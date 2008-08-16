package modules.vartest;

import java.util.LinkedList;
import java.util.List;

import data.AUserNode;
import data.annotation.ColorType;
import data.annotation.ComponentColor;
import data.annotation.ComponentInfo;
import data.annotation.Display;
import data.mod.IUserPacket;

/**
 * Node zum Variablen und Annotations testen
 * @author Christoph
 *
 */
@ComponentInfo(name = "VarTestNode")
@ComponentColor(color = ColorType.BLUE)
public class VarTestNode extends AUserNode {

    public void receive(IUserPacket packet) {
	// TODO Auto-generated method stub

    }

    @Display(name = "int exCount")
    public int integerExecuteCount;

    public VarTestNode() {
	integerExecuteCount = 0;

    }

    @Display(name = "test LIST")
    public List<Integer> getSomeList() {
	LinkedList<Integer> list = new LinkedList<Integer>();
	for (int i = 0; i < 10 * Math.random(); i++) {
	    list.add((int) (100 * Math.random()));
	}
	return list;
    }

    @Display(name = "int exCount()")
    public int getIntegerExecuteCount() {
	return integerExecuteCount;
    }

    @Display(name = "string exCount()")
    public String getStringExecuteCount() {
	return "" + integerExecuteCount;
    }

    @Display(name = "nameOverride")
    public String getName() {
	if (hasVariable("name"))
	    return getVariable("name").getData().toString();
	else
	    return "";
    }

    public void execute() {
	integerExecuteCount++;

    }

}
