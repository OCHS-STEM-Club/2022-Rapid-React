package frc.robot;



public class PIDController {

    Shooter shooterMotor = new Shooter(); 

    
    public double KP;
	public double KI;
	public double KD;
	public double KF;
	public int KIzone;
	public double KPeakOutput;


    public PIDController(double KP, double KI, double KD, double KF, int KIzone, double KPeakOutput){
		this.KP = KP;
		this.KI = KI;
		this.KD = KD;
		this.KF = KF;
		this.KIzone = KIzone;
		this.KPeakOutput = KPeakOutput;

    }
   

}
