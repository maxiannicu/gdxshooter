package com.maxiannicu.shooter.bodies.action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 3/2/17.
 */
public class ActionManager {
    private List<Action> actionList = new ArrayList<Action>();

    public void step(){
        List<Action> doneActions = new ArrayList<Action>();
        for (Action action:actionList) {
            action.act();
            if (action.done())
                doneActions.add(action);
        }

        actionList.removeAll(doneActions);
    }

    public void addAction(Action action){
        addAction(action,false);
    }

    public void addAction(Action action,boolean removeAffecting){
        if (removeAffecting)
            removeExistingAction(action);
        actionList.add(action);
    }

    private void removeExistingAction(Action action) {
        Action actionForBody = null;
        for (Action it : actionList) {
            if (it.getAffectedBody().equals(action.getAffectedBody()))
                actionForBody = it;
        }
        if (actionForBody != null){
            actionList.remove(actionForBody);
        }
    }
}
