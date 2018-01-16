package xiaoge;
//
public class Yang {

	int type=0;		//0—Ù:“ª    1“ı£∫--
	String name;
	int value;
	public Yang(int value){
		this.value = value%2;
		switch(this.value){
		case 1:
		{
			this.name = "—Ù";
			this.type = 0;
		}
			break;
		case 0:
		{
			this.name = "“ı";
			this.type = 1;
		}
		break;
		default:
			break;
		}
	}
}
