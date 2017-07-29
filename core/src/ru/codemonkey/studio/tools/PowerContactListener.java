package ru.codemonkey.studio.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;

import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.Player;

/**
 * Created by maximus on 29.07.2017.
 */

public class PowerContactListener implements ContactListener {
    private World world;
    private Player player;
    private ArrayList<Bullet> bullets;

    public PowerContactListener(World world, Player player, ArrayList<Bullet> bullets) {
        this.world = world;
        this.player = player;
        this.bullets = bullets;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
//        contact.setEnabled(false);
        if (contact.getFixtureA().getBody().getUserData().equals("player") && contact.getFixtureB().getBody().getUserData().equals("bullet")) {
            contact.setEnabled(false);
        }
        else if (contact.getFixtureB().getBody().getUserData().equals("player") && contact.getFixtureA().getBody().getUserData().equals("bullet")) {
            contact.setEnabled(false);
        }
        if (contact.getFixtureA().getBody().getUserData().equals("wall") && contact.getFixtureB().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureB().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
        }
        else if (contact.getFixtureB().getBody().getUserData().equals("wall") && contact.getFixtureA().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureA().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
