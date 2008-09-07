package vidis.data.var;

import java.util.Set;


/**
 * This interface defines all methods used for handling our variable system
 * 
 * @author Christoph
 * 
 */
public interface IVariableContainer extends IVariableChangeProducer {

	public AVariable getVariableById(String id);

	public Set<String> getVariableIds();

	public void registerVariable(AVariable var);

	public boolean hasVariable(String id);
}