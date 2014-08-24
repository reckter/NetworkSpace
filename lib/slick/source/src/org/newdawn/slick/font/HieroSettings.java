
package org.newdawn.slick.font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ConfigurableEffect;
import org.newdawn.slick.font.effects.ConfigurableEffect.Value;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Holds the settings needed to configure a UnicodeFont.
 * 
 * @author Nathan Sweet <misc@n4te.com>
 */
public class HieroSettings {
	/** The size of the font to be generated */
	private int fontSize = 12;
	/** True if the font is rendered bold */
	private boolean bold = false;
	/** True fi the font if rendered italic */
	private boolean italic = false;
	/** The padding applied in pixels to the top of the glyph rendered area */
	private int paddingTop;
	/** The padding applied in pixels to the left of the glyph rendered area */
	private int paddingLeft;
	/** The padding applied in pixels to the bottom of the glyph rendered area */
	private int paddingBottom;
	/** The padding applied in pixels to the right of the glyph rendered area */
	private int paddingRight;
	/** The padding applied in pixels to horizontal advance for each glyph */
	private int paddingAdvanceX;
	/** The padding applied in pixels to vertical advance for each glyph */
	private int paddingAdvanceY;
	/** The width of the glyph page generated */
	private int glyphPageWidth = 512;
	/** The height of the glyph page generated */
	private int glyphPageHeight = 512;
	/** The list of effects applied */
	private final List effects = new ArrayList();

	/**
	 * Default constructor for injection
	 */
	public HieroSettings() {
	}

	/**
	 * Create a new set of configuration from a file
	 * 
	 * @param hieroFileRef The file system or classpath location of the Hiero settings file.
	 * @throws org.newdawn.slick.SlickException if the file could not be read.
	 */
	public HieroSettings(String hieroFileRef) throws SlickException {
		this(ResourceLoader.getResourceAsStream(hieroFileRef));
	}
	
	/**
	 * Create a new set of configuration from a file
	 * 
	 * @param in The stream from which to read the settings from
	 * @throws org.newdawn.slick.SlickException if the file could not be read.
	 */
	public HieroSettings(InputStream in) throws SlickException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				line = line.trim();
				if (line.length() == 0) continue;
				String[] pieces = line.split("=", 2);
				String name = pieces[0].trim();
				String value = pieces[1];
				if (name.equals("font.size")) {
					fontSize = Integer.parseInt(value);
				} else if (name.equals("font.bold")) {
					bold = Boolean.valueOf(value).booleanValue();
				} else if (name.equals("font.italic")) {
					italic = Boolean.valueOf(value).booleanValue();
				} else if (name.equals("pad.top")) {
					paddingTop = Integer.parseInt(value);
				} else if (name.equals("pad.right")) {
					paddingRight = Integer.parseInt(value);
				} else if (name.equals("pad.bottom")) {
					paddingBottom = Integer.parseInt(value);
				} else if (name.equals("pad.left")) {
					paddingLeft = Integer.parseInt(value);
				} else if (name.equals("pad.advance.x")) {
					paddingAdvanceX = Integer.parseInt(value);
				} else if (name.equals("pad.advance.y")) {
					paddingAdvanceY = Integer.parseInt(value);
				} else if (name.equals("glyph.page.width")) {
					glyphPageWidth = Integer.parseInt(value);
				} else if (name.equals("glyph.page.height")) {
					glyphPageHeight = Integer.parseInt(value);
				} else if (name.equals("effect.class")) {
					try {
						effects.add(Class.forName(value).newInstance());
					} catch (Exception ex) {
						throw new SlickException("Unable to create effect instance: " + value, ex);
					}
				} else if (name.startsWith("effect.")) {
					// Set an effect value on the last added effect.
					name = name.substring(7);
					ConfigurableEffect effect = (ConfigurableEffect)effects.get(effects.size() - 1);
					List values = effect.getValues();
					for (Iterator iter = values.iterator(); iter.hasNext();) {
						Value effectValue = (Value)iter.next();
						if (effectValue.getName().equals(name)) {
							effectValue.setString(value);
							break;
						}
					}
					effect.setValues(values);
				}
			}
			reader.close();
		} catch (Exception ex) {
			throw new SlickException("Unable to load Hiero font file", ex);
		}
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingTop()
	 * 
	 * @return The padding for the top of the glyph area in pixels
	 */
	public int getPaddingTop () {
		return paddingTop;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingTop(int)
	 * 
	 * @param paddingTop The padding for the top of the glyph area in pixels
	 */
	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingLeft()
	 * 
	 * @return The padding for the left of the glyph area in pixels
	 */
	public int getPaddingLeft() {
		return paddingLeft;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingLeft(int)
	 * 
	 * @param paddingLeft The padding for the left of the glyph area in pixels
	 */
	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingBottom()
	 * 
	 * @return The padding for the bottom of the glyph area in pixels
	 */
	public int getPaddingBottom() {
		return paddingBottom;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingBottom(int)
	 * 
	 * @param paddingBottom The padding for the bottom of the glyph area in pixels
	 */
	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingRight()
	 * 
	 * @return The padding for the right of the glyph area in pixels
	 */
	public int getPaddingRight() {
		return paddingRight;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingRight(int)
	 * 
	 * @param paddingRight The padding for the right of the glyph area in pixels
	 */
	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingAdvanceX()
	 * 
	 * @return The padding for the horizontal advance of each glyph
	 */
	public int getPaddingAdvanceX() {
		return paddingAdvanceX;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingAdvanceX(int)
	 * 
	 * @param paddingAdvanceX The padding for the horizontal advance of each glyph
	 */
	public void setPaddingAdvanceX(int paddingAdvanceX) {
		this.paddingAdvanceX = paddingAdvanceX;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getPaddingAdvanceY()
	 * 
	 * @return The padding for the vertical advance of each glyph
	 */
	public int getPaddingAdvanceY() {
		return paddingAdvanceY;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setPaddingAdvanceY(int)
	 * 
	 * @param paddingAdvanceY The padding for the vertical advance of each glyph
	 */
	public void setPaddingAdvanceY(int paddingAdvanceY) {
		this.paddingAdvanceY = paddingAdvanceY;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getGlyphPageWidth()
	 * 
	 * @return The width of the generate glyph pages
	 */
	public int getGlyphPageWidth() {
		return glyphPageWidth;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setGlyphPageWidth(int)
	 * 
	 * @param glyphPageWidth The width of the generate glyph pages
	 */
	public void setGlyphPageWidth(int glyphPageWidth) {
		this.glyphPageWidth = glyphPageWidth;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getGlyphPageHeight()
	 * 
	 * @return The height of the generate glyph pages
	 */
	public int getGlyphPageHeight() {
		return glyphPageHeight;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#setGlyphPageHeight(int)
	 * 
	 * @param glyphPageHeight The height of the generate glyph pages
	 */
	public void setGlyphPageHeight(int glyphPageHeight) {
		this.glyphPageHeight = glyphPageHeight;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @return The point size of the font generated
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @param fontSize The point size of the font generated
	 */
	public void setFontSize (int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @return True if the font was generated in bold typeface
	 */
	public boolean isBold () {
		return bold;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @param bold True if the font was generated in bold typeface
	 */
	public void setBold (boolean bold) {
		this.bold = bold;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @return True if the font was generated in italic typeface
	 */
	public boolean isItalic () {
		return italic;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(String, int, boolean, boolean)
	 * @see org.newdawn.slick.UnicodeFont#UnicodeFont(java.awt.Font, int, boolean, boolean)
	 * 
	 * @param italic True if the font was generated in italic typeface
	 */
	public void setItalic (boolean italic) {
		this.italic = italic;
	}

	/**
	 * @see org.newdawn.slick.UnicodeFont#getEffects()
	 * 
	 * @return The list of effects applied to the text
	 */
	public List getEffects() {
		return effects;
	}

	/**
	 * Saves the settings to a file.
	 * 
	 * @param file The file we're saving to
	 * @throws java.io.IOException if the file could not be saved.
	 */
	public void save(File file) throws IOException {
		PrintStream out = new PrintStream(new FileOutputStream(file));
		out.println("font.size=" + fontSize);
		out.println("font.bold=" + bold);
		out.println("font.italic=" + italic);
		out.println();
		out.println("pad.top=" + paddingTop);
		out.println("pad.right=" + paddingRight);
		out.println("pad.bottom=" + paddingBottom);
		out.println("pad.left=" + paddingLeft);
		out.println("pad.advance.x=" + paddingAdvanceX);
		out.println("pad.advance.y=" + paddingAdvanceY);
		out.println();
		out.println("glyph.page.width=" + glyphPageWidth);
		out.println("glyph.page.height=" + glyphPageHeight);
		out.println();
		for (Iterator iter = effects.iterator(); iter.hasNext();) {
			ConfigurableEffect effect = (ConfigurableEffect)iter.next();
			out.println("effect.class=" + effect.getClass().getName());
			for (Iterator iter2 = effect.getValues().iterator(); iter2.hasNext();) {
				Value value = (Value)iter2.next();
				out.println("effect." + value.getName() + "=" + value.getString());
			}
			out.println();
		}
		out.close();
	}
}
