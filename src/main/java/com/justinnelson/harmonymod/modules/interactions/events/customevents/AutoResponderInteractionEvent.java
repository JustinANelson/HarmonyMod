package com.justinnelson.harmonymod.modules.interactions.events.customevents;

public class AutoResponderInteractionEvent {
    String name;
    String description;
    String content;

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setContent(String content) {this.content = content;}

    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getContent() {return content;}
}
