package com.server.Slooow;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.server.Slooow.MapPowerUp.powerType;

import org.springframework.web.socket.TextMessage;

public class ShieldPowerUp extends GenericPowerUp {

    public ShieldPowerUp(PlayerConected player,powerType myType) {
        super(player,0,myType);
    }

    @Override
    public void usePowerUp(){
        player.mySnail.activateShield();
        player.mySnail.setUsingPowerUp(false);
        player.mySnail.powerUpList.removeFirst();
        sendMessage();
        System.out.println("Caracol protegido");
    }


    public void sendMessage(PlayerConected player){
        JsonObject msg = new JsonObject();
			msg.addProperty("event", "USESHIELD");

			try {
				player.sessionLock.lock();
				player.getSession().sendMessage(new TextMessage(msg.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				player.sessionLock.unlock();
			}
    }
    

}