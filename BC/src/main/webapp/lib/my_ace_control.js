 var editor = ace.edit("editor");
editor.setTheme("ace/theme/Kuroir");
editor.session.setMode("ace/mode/io");

editor.setFontSize(17);

// add command to lazy-load keybinding_menu extension
editor.commands.addCommand({
    name: "showKeyboardShortcuts",
    bindKey: {win: "Ctrl-Alt-h", mac: "Command-Alt-h"},
    exec: function(editor) {
        ace.config.loadModule("ace/ext/keybinding_menu", function(module) {
            module.init(editor);
            editor.showKeyboardShortcuts()
        })
    } 
})
 
// add command to run reasoning 
editor.commands.addCommand({
    name: "run_reasoning",
    bindKey: {win: "Ctrl-x", mac: "Command-x"},
    exec: function(editor) {
    	createBN();
    } 
})
 