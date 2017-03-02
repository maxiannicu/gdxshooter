package com.maxiannicu.shooter.controller;

import com.maxiannicu.shooter.bodies.PhysicsBody;
import com.maxiannicu.shooter.bodies.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 3/2/17.
 */
public class ActionController implements Controller {
    private List<Action> actionList = new ArrayList<Action>();

    @Override
    public void step(){
        List<Action> doneActions = new ArrayList<Action>();
        for (Action action:actionList) {
            action.act();
            if (action.done())
                doneActions.add(action);
        }

        actionList.removeAll(doneActions);
    }

    public void addAction(Action action,boolean removeAffecting){
        if (removeAffecting)
            removeExistingActions(action.getAffectedBody());
        actionList.add(action);
    }

    public void removeExistingActions(PhysicsBody body) {
        Action actionForBody = null;
        for (Action it : actionList) {
            if (it.getAffectedBody().equals(body))
                actionForBody = it;
        }
        if (actionForBody != null){
            actionList.remove(actionForBody);
        }
    }
}
