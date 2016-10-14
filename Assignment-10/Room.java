// Room.java
// This class stores information for a "room". Which consists of a key
// description and an inner class Option. Option class stores information which
// consists of a tag and text for the option

import java.util.ArrayList;

public class Room {
    
    private String key, description;
    private ArrayList<Option> options;
    
    public Room(String key) {
        
        this.key = key;
        this.description = "";
        options = new ArrayList<>();
    }
    
    public void addDescription(String description) {
        this.description = this.description.concat(description + "\n");
    }
    
    public void addOption(Option option) {
        options.add(option);
    }
    
    public String            getKey()         { return key;                }
    public String            getDescription() { return description;        }
    public boolean           hasOptions()     { return !options.isEmpty(); }
    public ArrayList<Option> getOptions()     { return options;            }
    public String getOptionDestination(int index) {
        
        if(index >= options.size())
            return null;
        
        return options.get(index).getTag();
    }
    
    public static class Option {

        private String text, tag;
        
        public Option(String text) {       
            this.text = text;
        }
        
        public void setTag(String tag) {
            this.tag = tag;
        }
        
        public String getText() { return text; }
        public String getTag()  { return tag;  }
    }
}
