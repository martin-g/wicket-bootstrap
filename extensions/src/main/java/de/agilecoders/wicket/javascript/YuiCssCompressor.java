package de.agilecoders.wicket.javascript;

import com.google.common.collect.Lists;
import org.apache.wicket.css.ICssCompressor;

import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YuiCssCompressor implements ICssCompressor {

    private static final String PRESERVE_COMMENT = "___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_";
    private static final String PRESERVE_TOKEN = "___YUICSSMIN_PRESERVED_TOKEN_";
    private static final String PSEUDEO_CLASS = "___YUICSSMIN_PSEUDOCLASSCOLON___";

    private static final Pattern URL_PATTERN = Pattern.compile("url\\(\\s*([\"']?)data\\:");
    private static final Pattern STRING_PATTERN = Pattern.compile("(\"([^\\\\\"]|\\\\.|\\\\)*\")|(\'([^\\\\\']|\\\\.|\\\\)*\')");
    private static final Pattern BRACKETS_PATTERN = Pattern.compile("(^|\\})(([^\\{:])+:)+([^\\{]*\\{)");
    private static final Pattern BORDER_PATTERN = Pattern.compile("(?i)(border|border-top|border-right|border-bottom|border-right|outline|background):none(;|})");
    private static final Pattern RGB_PATTERN = Pattern.compile("rgb\\s*\\(\\s*([0-9,\\s]+)\\s*\\)");
    private static final Pattern HEX_PATTERN = Pattern.compile("(\\=\\s*?[\"']?)?" + "#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])" + "(:?\\}|[^0-9a-fA-F{][^{]*?\\})");
    private static final Pattern BROWSER_HACKS_PATTERN = Pattern.compile("(?i)(background-position|transform-origin|webkit-transform-origin|moz-transform-origin|o-transform-origin|ms-transform-origin):0(;|})");
    ;

    /**
     * Construct.
     */
    public YuiCssCompressor() {
    }

    /**
     * Leave data urls alone to increase parse performance.
     *
     * @param css             whole css content
     * @param preservedTokens a list of all preserved tokens
     * @return data url
     */
    private String extractDataUrls(final String css, final List<String> preservedTokens) {
        final int maxIndex = css.length() - 1;
        int appendIndex = 0;

        StringBuilder buffer = new StringBuilder();
        Matcher m = URL_PATTERN.matcher(css);

        /*
        * Since we need to account for non-base64 data urls, we need to handle
        * ' and ) being part of the data string. Hence switching to indexOf,
        * to determine whether or not we have matching string terminators and
        * handling sb appends directly, instead of using matcher.append* methods.
        */
        while (m.find()) {
            int startIndex = m.start() + 4;      // "url(".length()
            String terminator = m.group(1);     // ', " or empty (not quoted)

            if (terminator.length() == 0) {
                terminator = ")";
            }

            boolean foundTerminator = false;

            int endIndex = m.end() - 1;
            while (!foundTerminator && endIndex + 1 <= maxIndex) {
                endIndex = css.indexOf(terminator, endIndex + 1);

                if ((endIndex > 0) && (css.charAt(endIndex - 1) != '\\')) {
                    foundTerminator = true;
                    if (!")".equals(terminator)) {
                        endIndex = css.indexOf(")", endIndex);
                    }
                }
            }

            // Enough searching, start moving stuff over to the buffer
            buffer.append(css.substring(appendIndex, m.start()));

            if (foundTerminator) {
                String token = css.substring(startIndex, endIndex);
                token = token.replaceAll("\\s+", "");
                preservedTokens.add(token);

                String preserver = "url(" + PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___)";
                buffer.append(preserver);

                appendIndex = endIndex + 1;
            } else {
                // No end terminator found, re-add the whole match. Should we throw/warn here?
                buffer.append(css.substring(m.start(), m.end()));
                appendIndex = m.end();
            }
        }

        buffer.append(css.substring(appendIndex));

        return buffer.toString();
    }

    /**
     * compress the given css content and write it to given {@link Writer}.
     *
     * @param css the original css content
     * @param linebreakPosition max. line length
     */
    private String compress(String css, final int linebreakPosition) {
        Matcher m;

        int startIndex = 0, endIndex, i, max;
        List<String> preservedTokens = Lists.newArrayList();
        List<String> comments = Lists.newArrayList();
        String token;
        final int contentLength = css.length();
        String placeholder;

        css = this.extractDataUrls(css, preservedTokens);

        StringBuffer sb = new StringBuffer(css);

        // collect all comment blocks...
        while ((startIndex = sb.indexOf("/*", startIndex)) >= 0) {
            endIndex = sb.indexOf("*/", startIndex + 2);
            if (endIndex < 0) {
                endIndex = contentLength;
            }

            token = sb.substring(startIndex + 2, endIndex);
            comments.add(token);
            sb.replace(startIndex + 2, endIndex, PRESERVE_COMMENT + (comments.size() - 1) + "___");
            startIndex += 2;
        }
        css = sb.toString();

        // preserve strings so their content doesn't get accidentally minified
        sb = new StringBuffer();

        m = STRING_PATTERN.matcher(css);
        while (m.find()) {
            token = m.group();
            char quote = token.charAt(0);
            token = token.substring(1, token.length() - 1);

            // maybe the string contains a comment-like substring?
            // one, maybe more? put'em back then
            if (token.contains(PRESERVE_COMMENT)) {
                for (i = 0, max = comments.size(); i < max; i += 1) {
                    token = token.replace(PRESERVE_COMMENT + i + "___", comments.get(i));
                }
            }

            // minify alpha opacity in filter strings
            token = token.replaceAll("(?i)progid:DXImageTransform.Microsoft.Alpha\\(Opacity=", "alpha(opacity=");

            preservedTokens.add(token);
            String preserver = quote + PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___" + quote;
            m.appendReplacement(sb, preserver);
        }
        m.appendTail(sb);
        css = sb.toString();


        // strings are safe, now wrestle the comments
        for (i = 0, max = comments.size(); i < max; i += 1) {

            token = comments.get(i);
            placeholder = PRESERVE_COMMENT + i + "___";

            // ! in the first position of the comment means preserve
            // so push to the preserved tokens while stripping the !
            if (token.startsWith("!")) {
                preservedTokens.add(token);
                css = css.replace(placeholder, PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___");
                continue;
            }

            // \ in the last position looks like hack for Mac/IE5
            // shorten that to /*\*/ and the next one to /**/
            if (token.endsWith("\\")) {
                preservedTokens.add("\\");
                css = css.replace(placeholder, PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___");
                i = i + 1; // attn: advancing the loop
                preservedTokens.add("");
                css = css.replace(PRESERVE_COMMENT + i + "___", PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___");
                continue;
            }

            // keep empty comments after child selectors (IE7 hack)
            // e.g. html >/**/ body
            if (token.length() == 0) {
                startIndex = css.indexOf(placeholder);
                if (startIndex > 2) {
                    if (css.charAt(startIndex - 3) == '>') {
                        preservedTokens.add("");
                        css = css.replace(placeholder, PRESERVE_TOKEN + (preservedTokens.size() - 1) + "___");
                    }
                }
            }

            // in all other cases kill the comment
            css = css.replace("/*" + placeholder + "*/", "");
        }


        // Normalize all whitespace strings to single spaces. Easier to work with that way.
        css = css.replaceAll("\\s+", " ");

        // Remove the spaces before the things that should not have spaces before them.
        // But, be careful not to turn "p :link {...}" into "p:link{...}"
        // Swap out any pseudo-class colons with the token, and then swap back.
        sb = new StringBuffer();
        m = BRACKETS_PATTERN.matcher(css);
        while (m.find()) {
            String s = m.group();
            s = s.replaceAll(":", PSEUDEO_CLASS);
            s = s.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
            m.appendReplacement(sb, s);
        }
        m.appendTail(sb);

        css = sb.toString();
        // Remove spaces before the things that should not have spaces before them.
        css = css.replaceAll("\\s+([!{};:>+\\(\\)\\],])", "$1");
        // bring back the colon
        css = css.replaceAll(PSEUDEO_CLASS, ":");

        // retain space for special IE6 cases
        css = css.replaceAll(":first\\-(line|letter)(\\{|,)", ":first-$1 $2");

        // no space after the end of a preserved comment
        css = css.replaceAll("\\*/ ", "*/");

        // If there is a @charset, then only allow one, and push to the top of the file.
        css = css.replaceAll("^(.*)(@charset \"[^\"]*\";)", "$2$1");
        css = css.replaceAll("^(\\s*@charset [^;]+;\\s*)+", "$1");

        // Put the space back in some cases, to support stuff like
        // @media screen and (-webkit-min-device-pixel-ratio:0){
        css = css.replaceAll("\\band\\(", "and (");

        // Remove the spaces after the things that should not have spaces after them.
        css = css.replaceAll("([!{}:;>+\\(\\[,])\\s+", "$1");

        // remove unnecessary semicolons
        css = css.replaceAll(";+}", "}");

        // Replace 0(px,em,%) with 0.
        css = css.replaceAll("([\\s:])(0)(px|em|%|in|cm|mm|pc|pt|ex)", "$1$2");

        // Replace 0 0 0 0; with 0.
        css = css.replaceAll(":0 0 0 0(;|})", ":0$1");
        css = css.replaceAll(":0 0 0(;|})", ":0$1");
        css = css.replaceAll(":0 0(;|})", ":0$1");


        // Replace background-position:0; with background-position:0 0;
        // same for transform-origin
        sb = new StringBuffer();
        m = BROWSER_HACKS_PATTERN.matcher(css);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toLowerCase() + ":0 0" + m.group(2));
        }
        m.appendTail(sb);
        css = sb.toString();

        // Replace 0.6 to .6, but only when preceded by : or a white-space
        css = css.replaceAll("(:|\\s)0+\\.(\\d+)", "$1.$2");

        // Shorten colors from rgb(51,102,153) to #336699
        // This makes it more likely that it'll get further compressed in the next step.
        m = RGB_PATTERN.matcher(css);
        sb = new StringBuffer();
        while (m.find()) {
            String[] rgbcolors = m.group(1).split(",");
            StringBuilder hexcolor = new StringBuilder("#");
            for (i = 0; i < rgbcolors.length; i++) {
                int val = Integer.parseInt(rgbcolors[i]);
                if (val < 16) {
                    hexcolor.append("0");
                }
                hexcolor.append(Integer.toHexString(val));
            }
            m.appendReplacement(sb, hexcolor.toString());
        }
        m.appendTail(sb);
        css = sb.toString();

        // Shorten colors from #AABBCC to #ABC. Note that we want to make sure
        // the color is not preceded by either ", " or =. Indeed, the property
        //     filter: chroma(color="#FFFFFF");
        // would become
        //     filter: chroma(color="#FFF");
        // which makes the filter break in IE.
        // We also want to make sure we're only compressing #AABBCC patterns inside { }, not id selectors ( #FAABAC {} )
        // We also want to avoid compressing invalid values (e.g. #AABBCCD to #ABCD)
        m = HEX_PATTERN.matcher(css);
        sb = new StringBuffer();
        int index = 0;

        while (m.find(index)) {

            sb.append(css.substring(index, m.start()));

            boolean isFilter = (m.group(1) != null && !"".equals(m.group(1)));

            if (isFilter) {
                // Restore, as is. Compression will break filters
                sb.append(m.group(1) + "#" + m.group(2) + m.group(3) + m.group(4) + m.group(5) + m.group(6) + m.group(7));
            } else {
                if (m.group(2).equalsIgnoreCase(m.group(3)) &&
                    m.group(4).equalsIgnoreCase(m.group(5)) &&
                    m.group(6).equalsIgnoreCase(m.group(7))) {

                    // #AABBCC pattern
                    sb.append("#" + (m.group(3) + m.group(5) + m.group(7)).toLowerCase());

                } else {

                    // Non-compressible color, restore, but lower case.
                    sb.append("#" + (m.group(2) + m.group(3) + m.group(4) + m.group(5) + m.group(6) + m.group(7)).toLowerCase());
                }
            }

            index = m.end(7);
        }

        sb.append(css.substring(index));
        css = sb.toString();

        // border: none -> border:0
        sb = new StringBuffer();
        m = BORDER_PATTERN.matcher(css);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toLowerCase() + ":0" + m.group(2));
        }
        m.appendTail(sb);
        css = sb.toString();

        // shorter opacity IE filter
        css = css.replaceAll("(?i)progid:DXImageTransform.Microsoft.Alpha\\(Opacity=", "alpha(opacity=");

        // Remove empty rules.
        css = css.replaceAll("[^\\}\\{/;]+\\{\\}", "");

        // TODO: Should this be after we re-insert tokens. These could alter the break points. However then
        // we'd need to make sure we don't break in the middle of a string etc.
        if (linebreakPosition >= 0) {
            // Some source control tools don't like it when files containing lines longer
            // than, say 8000 characters, are checked in. The linebreak option is used in
            // that case to split long lines after a specific column.
            i = 0;
            int linestartpos = 0;
            StringBuilder linebreakBuilder = new StringBuilder(css);
            while (i < linebreakBuilder.length()) {
                char c = linebreakBuilder.charAt(i++);
                if (c == '}' && i - linestartpos > linebreakPosition) {
                    linebreakBuilder.insert(i, '\n');
                    linestartpos = i;
                }
            }

            css = linebreakBuilder.toString();
        }

        // Replace multiple semi-colons in a row by a single one
        // See SF bug #1980989
        css = css.replaceAll(";;+", ";");

        // restore preserved comments and strings
        for (i = 0, max = preservedTokens.size(); i < max; i++) {
            css = css.replace(PRESERVE_TOKEN + i + "___", preservedTokens.get(i));
        }

        // Trim the final string (for any leading or trailing white spaces)
        return css.trim();
    }

    @Override
    public String compress(String original) {
        return compress(original, 4000);
    }
}
