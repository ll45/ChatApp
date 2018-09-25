package pdg2_ll45.messages.Team;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import common.api.message.team.ITeamMsg;

/**
 * Class of image messages. Implements ITeamMsg.
 */
public class ImageMsg implements ITeamMsg, java.io.Serializable {
	
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -5795148088383727239L;
	
	/**
	 * The image
	 */
	private transient BufferedImage image;
	
	/**
	 * Constructor for an image message
	 * @param image		the buffered image
	 */
	public ImageMsg(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Writes stream. Override of serializable behavior.
	 * @param stream		Stream that is being read
	 * @throws IOException
	 */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
    	stream.defaultWriteObject();
        ImageIO.write(image, "png", stream);
    }

    /**
     * Reads in stream. Override of serializable behavior.
     * @param stream		Stream to be read
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
    	stream.defaultReadObject();
    	image = ImageIO.read(stream);
    }

	/**
	 * Gets image
	 * @return image 		a buffered image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
}
