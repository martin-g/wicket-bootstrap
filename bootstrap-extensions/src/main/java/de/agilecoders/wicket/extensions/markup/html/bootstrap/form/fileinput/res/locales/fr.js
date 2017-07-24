/*!
 * FileInput French Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";

    $.fn.fileinputLocales['fr'] = {
        fileSingle: 'fichier',
        filePlural: 'fichiers',
        browseLabel: 'Parcourir&hellip;',
        removeLabel: 'Retirer',
        removeTitle: 'Retirer les fichiers sélectionnés',
        cancelLabel: 'Annuler',
        cancelTitle: "Annuler l'envoi en cours",
        uploadLabel: 'Transférer',
        uploadTitle: 'Transférer les fichiers sélectionnés',
        msgNo: 'Non',
        msgNoFilesSelected: '',
        msgCancelled: 'Annulé',
        msgZoomModalHeading: 'Aperçu détaillé',
        msgSizeTooSmall: 'Le fichier "{name}" (<b>{size} KB</b>) est trop petit et doit avoir une taille minimale <b>{minSize} KB</b>.',
        msgSizeTooLarge: 'Le fichier "{name}" (<b>{size} Ko</b>) dépasse la taille maximale autorisée qui est de <b>{maxSize} Ko</b>.',
        msgFilesTooLess: 'Vous devez sélectionner au moins <b>{n}</b> {files} à transmettre.',
        msgFilesTooMany: 'Le nombre de fichier sélectionné <b>({n})</b> dépasse la quantité maximale autorisée qui est de <b>{m}</b>.',
        msgFileNotFound: 'Le fichier "{name}" est introuvable !',
        msgFileSecured: "Des restrictions de sécurité vous empêchent d'accéder au fichier \"{name}\".",
        msgFileNotReadable: 'Le fichier "{name}" est illisble.',
        msgFilePreviewAborted: 'Prévisualisation du fichier "{name}" annulée.',
        msgFilePreviewError: 'Une erreur est survenue lors de la lecture du fichier "{name}".',
        msgInvalidFileName: 'Le nom du fichier comporte certains caractères invalides "{name}".',
        msgInvalidFileType: 'Type de document invalide pour "{name}". Seuls les documents de type "{types}" sont autorisés.',
        msgInvalidFileExtension: 'Extension invalide pour le fichier "{name}". Seules les extensions "{extensions}" sont autorisées.',
        msgFileTypes: {
            'image': 'image',
            'html': 'HTML',
            'text': 'text',
            'video': 'video',
            'audio': 'audio',
            'flash': 'flash',
            'pdf': 'PDF',
            'object': 'object'
        },
        msgUploadAborted: 'Le téléchargement du fichier a été interrompu',
        msgUploadThreshold: 'Téléchargement en cours...',
        msgUploadEmpty: 'Pas de données valides à télécharger.',
        msgValidationError: 'Erreur de validation',
        msgLoading: 'Transmission du fichier {index} sur {files}&hellip;',
        msgProgress: 'Transmission du fichier {index} sur {files} - {name} - {percent}% faits.',
        msgSelected: '{n} {files} sélectionné(s)',
        msgFoldersNotAllowed: 'Glissez et déposez uniquement des fichiers ! {n} répertoire(s) exclu(s).',
        msgImageWidthSmall: 'Largeur de fichier image "{name}" doit être d\'au moins {size} px.',
        msgImageHeightSmall: 'Hauteur de fichier image "{name}" doit être d\'au moins {size} px.',
        msgImageWidthLarge: 'Largeur de fichier image "{name}" ne peut pas dépasser {size} px.',
        msgImageHeightLarge: 'Hauteur de fichier image "{name}" ne peut pas dépasser {size} px.',
        msgImageResizeError: "Impossible d'obtenir les dimensions de l'image à redimensionner.",
        msgImageResizeException: "Erreur lors du redimensionnement de l'image.<pre>{errors}</pre>",
        msgAjaxError: 'Quelque chose s\'est mal passé avec l\'opération {operation}. Merci de recommencer plus tard !',
        msgAjaxProgressError: '{operation} échouée',
        ajaxOperations: {
            deleteThumb: 'Fichier détruit',
            uploadThumb: '1 fichier téléchargé',
            uploadBatch: '1 fichier téléchargé',
            uploadExtra: 'Données téléchargées'
        },
        dropZoneTitle: 'Glissez et déposez les fichiers ici&hellip;',
        dropZoneClickTitle: '<br>(ou cliquer pour sélectionner {files})',
        fileActionSettings: {
            removeTitle: 'Supprimer le fichier',
            uploadTitle: 'Télécharger un fichier',
            zoomTitle: 'Voir les détails',
            dragTitle: 'Move / Rearrange',
            indicatorNewTitle: 'Pas encore téléchargé',
            indicatorSuccessTitle: 'Posté',
            indicatorErrorTitle: 'Ajouter erreur',
            indicatorLoadingTitle: 'ajout ...'
        },
        previewZoomButtonTitles: {
            prev: 'View previous file',
            next: 'View next file',
            toggleheader: 'Afficher l\'entête',
            fullscreen: 'Passer en mode plein écran',
            borderless: 'Passer en mode sans bordure',
            close: 'Fermer la visualisation détaillée'
        }
    };
})(window.jQuery);
