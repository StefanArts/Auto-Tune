package unprotesting.com.github.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import unprotesting.com.github.Main;
import unprotesting.com.github.API.HttpPostRequestor;
import unprotesting.com.github.Logging.Logging;

public class APIKeyCheckEvent extends Event{

    @Getter
    private Boolean correctAPIKey;

    @Getter
    private final HandlerList Handlers = new HandlerList();

    public APIKeyCheckEvent(){
        Main.setRequestor(new HttpPostRequestor());
        while(Main.getRequestor().getCorrectAPIKey() == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        this.correctAPIKey = Main.getRequestor().getCorrectAPIKey();
        if (!this.correctAPIKey){
            Logging.error(6);
            Main.closePlugin();
        }
    }
    
}