package com.example.fincare_uat;

class User {

   public String name;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String hometown;
}
