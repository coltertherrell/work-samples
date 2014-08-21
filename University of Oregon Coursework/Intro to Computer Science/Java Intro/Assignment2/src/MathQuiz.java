import java.util.Random;
import javax.swing.JOptionPane;


public class MathQuiz {
	
	public static void main(String[] args)
	{
		JOptionPane application = new JOptionPane();
		String questions = application.showInputDialog("Enter number of questions: ");
		int quesNum = Integer.parseInt(questions);
		int quesCount = 0;
		double correct = 0;
		double wrong = 0;
		Random generator = new Random();
		
		while( quesCount < quesNum) {
			int num1 = 1 + generator.nextInt( 9 );
			int num2 = 1 + generator.nextInt( 9 );
			String input1 = "What is " + Integer.toString(num1) + " times " + Integer.toString(num2);
			String UserAnswer = application.showInputDialog(input1);
			int answer = Integer.parseInt(UserAnswer);
			if( answer == (num1 * num2)) {
				application.showMessageDialog(null,"Correct!");
				correct = correct + 1;
			} else {
				application.showMessageDialog(null,"Sorry that's wrong");
				wrong = wrong + 1;
			}
			quesCount = quesCount + 1;
		}
		double percCorrect = correct/(correct + wrong);
		String result = String.format("%.2f", percCorrect);
		application.showMessageDialog(null,"You got " + result +" correct");
	}
}
