define([
        'backbone',
        'communicator',
        'hbs!tmpl/welcome'
    ],

    function(Backbone, Communicator, Welcome_tmpl) {
        'use strict';

        var welcomeTmpl = Welcome_tmpl;

        var App = new Backbone.Marionette.Application();

        App.isOffline = function() {
            return true;
        };

        /* Add application regions here */
        App.addRegions({});

        /* Add initializers here */
        App.addInitializer(function() {
            if (App.isOffline()) {
                Communicator.mediator.trigger("APP:LOGIN");
            } else {
                Communicator.mediator.trigger("APP:START");
                document.body.innerHTML = welcomeTmpl({
                    success: "CONGRATS!"
                });
            }
        });

        return App;
    });