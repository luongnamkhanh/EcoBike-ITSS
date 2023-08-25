package application.controller;

public class StdBikeCalculator implements ICalculator {
	public float rentFees(float rentTime) {
		if(rentTime >= 1) {
			if(rentTime <= 30) {
				return (float)10000;
			}else {
				float fees = 10000;
				float temp = rentTime - 30;

				int factor = (int) (temp / 15);
				float remainder = (temp % 15);

				if(remainder == 0) {
					fees += factor * 3000;
				}else {
					fees += (factor + 1) * 3000;
				}
				return fees;
			}
		}else {
			return 0;
		}

	}
}