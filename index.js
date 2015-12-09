'use strict';

var SimpleMenu = require('react-native').NativeModules.UISimpleMenuManager;

module.exports = {
  show: function(options, callback) {
    SimpleMenu.showDialog(options.title, options.options, callback);
  }
};
