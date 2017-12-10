package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.LineBodyBehavior;
import com.example.sabila.handymind.LineHeadBehavior;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.lineBehaviors.LineDashedBody;
import com.example.sabila.handymind.lineBehaviors.LineWithArrowHead;
import com.example.sabila.handymind.shapes.Line;

/**
 * Created by Sabila on 12/10/2017.
 */

public class LineTool extends Tool {
    private Line line;
    private LineBodyBehavior bodyBehavior;
    private LineHeadBehavior headBehavior;

    public LineTool(LineBodyBehavior bodyBehavior, LineHeadBehavior headBehavior) {
        this.bodyBehavior = bodyBehavior;
        this.headBehavior = headBehavior;
    }

    @Override
    public Shape createShape(float x, float y) {
        line = new Line(x, y, bodyBehavior, headBehavior);
        return line;
    }

    @Override
    public void drag(float x, float y) {
        line.setxEnd(x);
        line.setyEnd(y);
    }

    public void setBody(){
        line.setDashedLine();
    }

    public void setDashedLine() {
        this.bodyBehavior = new LineDashedBody();
    }

    public void setArrowHead() {
        this.headBehavior = new LineWithArrowHead();
    }
}
