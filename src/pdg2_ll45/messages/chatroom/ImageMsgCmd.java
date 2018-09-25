package pdg2_ll45.messages.Team;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.function.Supplier;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import common.api.team.ATeamDPAlgoCmd;
import common.api.team.TeamDataPacket;
import common.api.team.ICmd2ModelAdapter;
import common.api.user.AUserDPAlgoCmd;
import provided.datapacket.ADataPacket;
import provided.extvisitor.IExtVisitorHost;

/**
 * Class of image supplied. Implements supplier.
 */
final class ImgSupplier implements Supplier<JComponent> {
	
	/**
	 * A JComponent
	 */
	private JComponent comp;
	
	/**
	 * Constructor for image supplier
	 * @param comp		the JComponent that is the supplier
	 */
	public ImgSupplier(JComponent comp) {
		this.comp = comp;
	}
	
	/**
	 * Gets the JComponent
	 */
	@Override
	public JComponent get() {
		return comp;
	}
	
}

/**
 * Image message class that extends abstract Team data package algorithm command
 */
public class ImageMsgCmd extends ATeamDPAlgoCmd<ImageMsg> {
	
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 2472025231781681283L;
	
	/**
	 * Command to model adapter
	 */
	private ICmd2ModelAdapter adpt;

	/**
	 * Constructor for image message command
	 * @param adpt		the command to model adapter
	 */
	public ImageMsgCmd(ICmd2ModelAdapter adpt) {
		this.setCmd2ModelAdpt(adpt);
	}
	
	/**
	 * Applies to Object
	 */
	@Override
	public Void apply(Class<?> index, TeamDataPacket<ImageMsg> host, Void... params) {
		BufferedImage image = host.getData().getImage();
		JLabel picLabel = new JLabel(new ImageIcon(image));
		System.out.println("model adpt null " + (this.getCmd2ModelAdpt() == null));
		this.getCmd2ModelAdpt().buildComponent(new ImgSupplier(picLabel));
		return null;
	}

}
