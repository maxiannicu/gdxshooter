package com.maxiannicu.shooter.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

/**
 * Created by nicu on 3/2/17.
 */
public class Renderer {
    private final Map<Integer,List<Renderable>> renderMap = new HashMap<Integer, List<Renderable>>();
    private final SpriteBatch batch = new SpriteBatch();

    public void add(int zIndex,Renderable renderable){
        getListForIndex(zIndex).add(renderable);
    }

    public void remove(int zIndex,Renderable renderable){
        List<Renderable> listForIndex = getListForIndex(zIndex);
        if (listForIndex.contains(renderable)){
            listForIndex.remove(renderable);
        }
    }

    public void render(){
        batch.begin();
        int[] zIndexes = getZIndexes();
        for (int index : zIndexes){
            renderForIndex(index);
        }
        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    private void renderForIndex(int index) {
        List<Renderable> listForIndex = getListForIndex(index);
        for (Renderable renderable : listForIndex){
            renderable.render(batch);
        }
    }

    private int[] getZIndexes(){
        Set<Integer> keySet = renderMap.keySet();
        int[] keys = new int[keySet.size()];
        int i = 0;
        for (int key : keySet){
            keys[i++] = key;
        }
        Arrays.sort(keys);

        return keys;
    }

    private List<Renderable> getListForIndex(int zIndex){
        List<Renderable> list = renderMap.get(zIndex);
        if (list == null) {
            list = new ArrayList<Renderable>();
            renderMap.put(zIndex,list);
        }

        return list;
    }
}
