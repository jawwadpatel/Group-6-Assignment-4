public class Dice {
			
	private DiceValue value;
	
	public Dice() {
		value =  DiceValue.getRandom();
	}
	
	public DiceValue getValue() {
		return value;
	}

	public DiceValue roll() {
		this.value = DiceValue.getRandom();
		return this.value;
	}		
	
	public String toString() {
		return value.toString();
	}
}
