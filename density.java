import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class density{
  	static final DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String args[]){
		System.out.println(" +---------+--------------+------+");
		System.out.println(" |Res      | Diag|    Size|  P/mm|");
		System.out.println(" +---------+--------------+------+");
		if(args.length==0){
			calculate(1920, 1080, 15.6); line();
			calculate(1920, 1080, 21.0); line();
			calculate(1920, 1080, 24.0); line();
			calculate(2560, 1440, 27.0); line();
			calculate(2560, 1440, 32.0); line();
			calculate(3840, 2160, 32.0);
		}else
		calculate(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Double.parseDouble(args[2]));
		line();
	}

	static void line(){
		System.out.println("\n"+" +---------+--------------+------+");
	}
	static void calculate(int x, int y, Double diagonal){
		String str = (" |"+x +"x"+ y + "| "); 
		if(diagonal.toString().length()<4){str+=" ";}
		str+=diagonal; str+="|";

		Double ratio = (double)y/x; //dogging happening
		Double size = area(diagonal, ratio);
		size = (double)Math.round(size);

		for(int i=size.toString().length(); i<10;i++){str+=" ";}
		str+=Math.round(size); str+="|";

		String ppmm = ppmm(x, y, size);

		for(int i=ppmm.length(); i<6;i++){str+=" ";}
		str+=ppmm; str+="|";
		System.out.print(str);
	}

	
	static Double mm(Double inch){
		return inch*25.4;
	}
	static Double area(Double diagonal, Double ratio){
		Double base = 0.0;
		Double height =0.0;
		Double c_squared = mm(diagonal)*mm(diagonal);
		Double b_squared = c_squared/(ratio*ratio+1);
		Double a_squared = c_squared - b_squared;
		base = Math.pow(b_squared, 0.5);
		height = Math.pow(a_squared, 0.5);
		return base*height;
	}
	static String ppmm(int x, int y, Double size){
		Double d = (double)x*y/size;
		return df.format(d);
	}
}