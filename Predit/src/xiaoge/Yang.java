package xiaoge;
//
public class Yang {

	int type=0;		//0��:һ    1����--
	String name;
	int value;
	public Yang(int value){
		this.value = value%2;
		switch(this.value){
		case 1:
		{
			this.name = "��";
			this.type = 0;
		}
			break;
		case 0:
		{
			this.name = "��";
			this.type = 1;
		}
		break;
		default:
			break;
		}
	}
}
