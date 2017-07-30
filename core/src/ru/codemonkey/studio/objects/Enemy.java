package ru.codemonkey.studio.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;
import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.tools.ControlHandler;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Enemy extends Sprite implements Disposable{
    private PointLight pointLight;

    private Body body;
    private Sound sound;
    private int HP;
    private boolean isAlive;
    private float volume;

    public Enemy(TextureRegion texture, World world, TiledMap map, RayHandler rayHandler, float volume, Vector2 pos){
        super(texture);
        this.volume = volume;
        HP = 100;
        isAlive = true;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(pos);
        body = world.createBody(bDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(16 / Power.S);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;

        body.createFixture(fDef);
        body.setUserData("enemy");
    }

    public void update(Vector2 posHero){
        goToHero(posHero);
        setPosition(body.getPosition().x * Power.S - getWidth() / 2, body.getPosition().y * Power.S - getHeight() / 2);
    }

    public void goToHero(Vector2 posHero){
        Vector2 c = posHero.sub(body.getPosition());
        c = DETControlHandler.vectorSinCos(c);
        c.x *= 3.3f;
        c.y *= 3.3f;
        body.setLinearVelocity(c);
//        c.sub(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        setRotation(c.angle());
    }




    @Override
    public void dispose() {

    }
}
