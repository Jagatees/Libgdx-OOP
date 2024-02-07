package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMapHelper {
    private TiledMap tileMap;

    public  TileMapHelper(){

    }

    public OrthogonalTiledMapRenderer setup(){
        tileMap = new TmxMapLoader().load("assets/map.tmx");
        return new OrthogonalTiledMapRenderer(tileMap);
    }
}
