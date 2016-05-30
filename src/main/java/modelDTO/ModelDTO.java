package modelDTO;

import java.io.Serializable;

@FunctionalInterface
public interface ModelDTO<O> extends Serializable{

	public void map(O realObject);
}
