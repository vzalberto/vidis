package vis.input;

import java.awt.event.KeyEvent;
/**
 * Die Enumeration Key beschreibt jede Taste auf der Tastatur
 * @author Christoph
 *
 */
// TODO Maustasten?
public enum Key {
	// TODO Fill Special Keys
	
	KEY_A(KeyEvent.VK_A),
	KEY_B(KeyEvent.VK_B),
	KEY_C(KeyEvent.VK_C),
	KEY_D(KeyEvent.VK_D),
    KEY_E(KeyEvent.VK_E),
    KEY_F(KeyEvent.VK_F),
    KEY_G(KeyEvent.VK_G),
    KEY_H(KeyEvent.VK_H),
    KEY_I(KeyEvent.VK_I),
    KEY_J(KeyEvent.VK_J),
    KEY_K(KeyEvent.VK_K),
    KEY_L(KeyEvent.VK_L),
    KEY_M(KeyEvent.VK_M),
    KEY_N(KeyEvent.VK_N),
    KEY_O(KeyEvent.VK_O),
    KEY_P(KeyEvent.VK_P),
    KEY_Q(KeyEvent.VK_Q),
    KEY_R(KeyEvent.VK_R),
    KEY_S(KeyEvent.VK_S),
    KEY_T(KeyEvent.VK_T),
    KEY_U(KeyEvent.VK_U),
    KEY_V(KeyEvent.VK_V),
    KEY_W(KeyEvent.VK_W),
    KEY_X(KeyEvent.VK_X),
    KEY_Y(KeyEvent.VK_Y),
    KEY_Z(KeyEvent.VK_Z),
    KEY_1(KeyEvent.VK_1),
    KEY_2(KeyEvent.VK_2),
    KEY_3(KeyEvent.VK_3),
    KEY_4(KeyEvent.VK_4),
    KEY_5(KeyEvent.VK_5),
    KEY_6(KeyEvent.VK_6),
    KEY_7(KeyEvent.VK_7),
    KEY_8(KeyEvent.VK_8),
    KEY_9(KeyEvent.VK_9),
    KEY_0(KeyEvent.VK_0),
    // Funktion Keys
    KEY_F1(KeyEvent.VK_F1),
    KEY_F2(KeyEvent.VK_F2),
    KEY_F3(KeyEvent.VK_F3),
    KEY_F4(KeyEvent.VK_F4),
    KEY_F5(KeyEvent.VK_F5),
    KEY_F6(KeyEvent.VK_F6),
    KEY_F7(KeyEvent.VK_F7),
    KEY_F8(KeyEvent.VK_F8),
    KEY_F9(KeyEvent.VK_F9),
    KEY_F10(KeyEvent.VK_F10),
    KEY_F11(KeyEvent.VK_F11),
    KEY_F12(KeyEvent.VK_F12),
    // Special Keys
    KEY_ESCAPE(KeyEvent.VK_ESCAPE),
    KEY_CONTROL(KeyEvent.VK_CONTROL),
    KEY_SHIFT(KeyEvent.VK_SHIFT),
    KEY_ALT(KeyEvent.VK_ALT),
    KEY_BACKSPACE(8),
    
    KEY_SS(0),
    
    KEY_ENTER(KeyEvent.VK_ENTER),
    KEY_BEGIN(KeyEvent.VK_BEGIN),
    KEY_END(KeyEvent.VK_END),
    KEY_INSERT(KeyEvent.VK_INSERT),
    KEY_DELETE(KeyEvent.VK_DELETE),
    KEY_PAGE_UP(KeyEvent.VK_PAGE_UP),
    KEY_PAGE_DOWN(KeyEvent.VK_PAGE_DOWN);
	 
	
	private int keycode;
	private Key(int keycode)
	{
		this.keycode=keycode;
	}
	public int getKeyCode(){
		return keycode;
	}
	public static Key byCode(int keyCode2) {
		//TODO Fill Keys
		switch (keyCode2){
		// A - Z
		case KeyEvent.VK_A: return Key.KEY_A;
		case KeyEvent.VK_B: return Key.KEY_B;
		case KeyEvent.VK_C: return Key.KEY_C;
		case KeyEvent.VK_D: return Key.KEY_D;
		case KeyEvent.VK_E: return Key.KEY_E;
		case KeyEvent.VK_F: return Key.KEY_F;
		case KeyEvent.VK_G: return Key.KEY_G;
		case KeyEvent.VK_H: return Key.KEY_H;
		case KeyEvent.VK_I: return Key.KEY_I;
		case KeyEvent.VK_J: return Key.KEY_J;
		case KeyEvent.VK_K: return Key.KEY_K;
		case KeyEvent.VK_L: return Key.KEY_L;
		case KeyEvent.VK_M: return Key.KEY_M;
		case KeyEvent.VK_N: return Key.KEY_N;
		case KeyEvent.VK_O: return Key.KEY_O;
		case KeyEvent.VK_P: return Key.KEY_P;
		case KeyEvent.VK_Q: return Key.KEY_Q;
		case KeyEvent.VK_R: return Key.KEY_R;
		case KeyEvent.VK_S: return Key.KEY_S;
		case KeyEvent.VK_T: return Key.KEY_T;
		case KeyEvent.VK_U: return Key.KEY_U;
		case KeyEvent.VK_V: return Key.KEY_V;
		case KeyEvent.VK_W: return Key.KEY_W;
		case KeyEvent.VK_X: return Key.KEY_X;
		case KeyEvent.VK_Y: return Key.KEY_Y;
		case KeyEvent.VK_Z: return Key.KEY_Z;
		// 0 - 9
		case KeyEvent.VK_0: return Key.KEY_0;
		case KeyEvent.VK_1: return Key.KEY_1;
		case KeyEvent.VK_2: return Key.KEY_2;
		case KeyEvent.VK_3: return Key.KEY_3;
		case KeyEvent.VK_4: return Key.KEY_4;
		case KeyEvent.VK_5: return Key.KEY_5;
		case KeyEvent.VK_6: return Key.KEY_6;
		case KeyEvent.VK_7: return Key.KEY_7;
		case KeyEvent.VK_8: return Key.KEY_8;
		case KeyEvent.VK_9: return Key.KEY_9;
		// Funtion Keys
		case KeyEvent.VK_F1: return Key.KEY_F1;
		case KeyEvent.VK_F2: return Key.KEY_F2;
		case KeyEvent.VK_F3: return Key.KEY_F3;
		case KeyEvent.VK_F4: return Key.KEY_F4;
		case KeyEvent.VK_F5: return Key.KEY_F5;
		case KeyEvent.VK_F6: return Key.KEY_F6;
		case KeyEvent.VK_F7: return Key.KEY_F7;
		case KeyEvent.VK_F8: return Key.KEY_F8;
		case KeyEvent.VK_F9: return Key.KEY_F9;
		case KeyEvent.VK_F10: return Key.KEY_F10;
		case KeyEvent.VK_F11: return Key.KEY_F11;
		case KeyEvent.VK_F12: return Key.KEY_F12;
		// Special Keys
		case KeyEvent.VK_ESCAPE: return Key.KEY_ESCAPE;
		case KeyEvent.VK_CONTROL: return Key.KEY_CONTROL;
		case KeyEvent.VK_SHIFT: return Key.KEY_SHIFT;
		case KeyEvent.VK_ALT: return Key.KEY_ALT;
		case KeyEvent.VK_ENTER: return Key.KEY_ENTER;
		case KeyEvent.VK_INSERT: return Key.KEY_INSERT;
		case KeyEvent.VK_DELETE: return Key.KEY_DELETE;
		case KeyEvent.VK_BEGIN: return Key.KEY_BEGIN;
		case KeyEvent.VK_END: return Key.KEY_END;
		case KeyEvent.VK_PAGE_UP: return Key.KEY_PAGE_UP;
		case KeyEvent.VK_PAGE_DOWN: return Key.KEY_PAGE_DOWN;
		default: return Key.KEY_0;
		}
	}
}
