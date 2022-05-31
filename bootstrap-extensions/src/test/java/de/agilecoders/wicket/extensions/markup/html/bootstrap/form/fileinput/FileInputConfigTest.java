package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.wicket.util.lang.Bytes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FileInputConfigTest {
    private FileInputConfig config;

    @BeforeEach
    void setUp() {
        config = new FileInputConfig();
    }

    @Nested
    class MaxFileSizeTests {
        @Test
        void whenNotSetting_isZero() {
            assertThat(config.maxFileSize(), is(Bytes.kilobytes(0.0)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(0));
        }

        @Test
        void whenSettingToZero_isZero() {
            config = config.maxFileSize(Bytes.bytes(0));
            assertThat(config.maxFileSize(), is(Bytes.kilobytes(0.0)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(0));
        }

        @Test
        void whenSetting_isSetToKb() {
            config = config.maxFileSize(Bytes.bytes(1024.0));
            assertThat(config.maxFileSize(), is(Bytes.kilobytes(1.0)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(1));
        }

        @Test
        void whenSettingToBytes_isRoundedToKb_down() {
            config = config.maxFileSize(Bytes.bytes(1535));
            assertThat(config.maxFileSize(), is(Bytes.bytes(1024)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(1));
        }

        @Test
        void whenSettingToBytes_isRoundedToKb_up() {
            config = config.maxFileSize(Bytes.bytes(1537));
            assertThat(config.maxFileSize(), is(Bytes.bytes(2048)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(2));
        }

        @Test
        void whenSettingTwice_isSetToSecondValue() {
            config = config.maxFileSize(Bytes.bytes(1024));
            config = config.maxFileSize(Bytes.megabytes(10));
            assertThat(config.maxFileSize(), is(Bytes.bytes(10_485_760)));
            assertThat(config.get(FileInputConfig.MaxFileSize), is(10_240));
        }
    }

    @Nested
    class MinFileSizeTests {
        @Test
        void whenNotSetting_isZero() {
            assertThat(config.minFileSize(), is(Bytes.kilobytes(0.0)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(0));
        }

        @Test
        void whenSettingToZero_isZero() {
            config = config.minFileSize(Bytes.bytes(0));
            assertThat(config.minFileSize(), is(Bytes.kilobytes(0.0)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(0));
        }

        @Test
        void whenSetting_isSetToKb() {
            config = config.minFileSize(Bytes.bytes(1024.0));
            assertThat(config.minFileSize(), is(Bytes.kilobytes(1.0)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(1));
        }

        @Test
        void whenSettingToBytes_isRoundedToKb_down() {
            config = config.minFileSize(Bytes.bytes(1535));
            assertThat(config.minFileSize(), is(Bytes.bytes(1024)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(1));
        }

        @Test
        void whenSettingToBytes_isRoundedToKb_up() {
            config = config.minFileSize(Bytes.bytes(1537));
            assertThat(config.minFileSize(), is(Bytes.bytes(2048)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(2));
        }

        @Test
        void whenSettingTwice_isSetToSecondValue() {
            config = config.minFileSize(Bytes.bytes(1024));
            config = config.minFileSize(Bytes.megabytes(10));
            assertThat(config.minFileSize(), is(Bytes.bytes(10_485_760)));
            assertThat(config.get(FileInputConfig.MinFileSize), is(10_240));
        }
    }
}
