package com.threelittlepigs.codecool.libraryManager.Enums;

public enum Genre {
    Genre(null),
    SciFi(Genre),
    History(Genre),
    Tales(Genre),
    Educational(Genre),
    Christian(SciFi),
    Steampunk(SciFi),
    Science(SciFi),
    Folk(Tales),
    Cumultative(Tales),
    Realistic(Tales),
    Religious(Tales),
    National(History),
    Chronicles(History),
    Encyclopedias(Educational),
    Programming(Educational),
    Fantasy(Tales)
    ;
    private Genre parent = null;

    private Genre(Genre parent) {
        this.parent = parent;
    }
}