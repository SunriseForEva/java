package classes;

public class Stage {
	private Stage() {
	}

	private static class StageSingletoneHolder {
		static Stage instance = new Stage();

	}

	public static Stage getInstance() {
		return StageSingletoneHolder.instance;
	}
}
