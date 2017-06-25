/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
        
        
        //ckfinder
        config.filebrowserBrowseUrl = 'http://localhost:8080/CKFinderJava/ckfinder/ckfinder.html';
        config.filebrowserImageBrowseUrl = 'http://localhost:8080/CKFinderJava/ckfinder/ckfinder.html?Type=Images';
        config.filebrowserFlashBrowseUrl = 'http://localhost:8080/CKFinderJava/ckfinder/ckfinder.html?type=Flash';        
        config.filebrowserUploadUrl = 'http://localhost:8080/CKFinderJava/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';        
	config.filebrowserImageUploadUrl = 'http://localhost:8080/CKFinderJava/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Image';
	config.filebrowserFlashUploadUrl = 'http://localhost:8080/CKFinderJava/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';        
        config.filebrowserWindowWidth = '1000';
        config.filebrowserWindowHeight = '600';        
        config.language = 'ru';
        
        //kcfinder
//        config.filebrowserBrowseUrl = 'js/kcfinder/browse.php?opener=ckeditor&type=files';
//        config.filebrowserImageBrowseUrl = 'js/kcfinder/browse.php?opener=ckeditor&type=images';
//        config.filebrowserFlashBrowseUrl = 'js/kcfinder/browse.php?opener=ckeditor&type=flash';
//        config.filebrowserUploadUrl = 'js/kcfinder/upload.php?opener=ckeditor&type=files';
//        config.filebrowserImageUploadUrl = 'js/kcfinder/upload.php?opener=ckeditor&type=images';
//        config.filebrowserFlashUploadUrl = 'js/kcfinder/upload.php?opener=ckeditor&type=flash';
};
