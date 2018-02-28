// Generated on Tue Feb 27 23:45:49 EST 2018
// DTD/Schema  :    null

package com.janusresearch.tdXmlPlugin.dom.config;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

/**
 * null:MoviesType interface.
 */
public interface Movies extends DomElement {

	/**
	 * Returns the list of Movie children.
	 * @return the list of Movie children.
	 */
	@NotNull
	@SubTagList("Movie")
	java.util.List<Movie> getMovies();
	/**
	 * Adds new child to the list of Movie children.
	 * @return created child
	 */
	@SubTag("Movie")
	Movie addMovie();


}
