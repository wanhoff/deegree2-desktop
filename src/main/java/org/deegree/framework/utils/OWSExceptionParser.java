//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 and
 lat/lon GmbH

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.framework.utils;

import org.deegree.framework.log.ILogger;
import org.deegree.framework.log.LoggerFactory;
import org.deegree.framework.xml.NamespaceContext;
import org.deegree.framework.xml.XMLFragment;
import org.deegree.framework.xml.XMLTools;
import org.deegree.ogcbase.CommonNamespaces;

/**
 * The <code></code> class TODO add class documentation here.
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 * 
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 * 
 */
public class OWSExceptionParser {

    private static final ILogger LOG = LoggerFactory.getLogger( OWSExceptionParser.class );

    private static final String OWSMSG = "ows:Exception/wfs:ExceptionText";

    private static final String OWSLOC = "wfs:Exception/@locator";

    private static final String WMS111MSG = "ServiceException";

    private static NamespaceContext nsc = CommonNamespaces.getNamespaceContext();

    /**
     * 
     * @param exception
     * @return first field contains message, second locator
     */
    public static String[] parseException( XMLFragment exception ) {
        String[] result = new String[] { "no message", "no locator" };
        String nsp = exception.getRootElement().getNamespaceURI();
        try {
            if ( nsp == null || "".equals( nsp ) ) {
                result[0] = XMLTools.getRequiredNodeAsString( exception.getRootElement(), WMS111MSG, nsc );
            } else if ( CommonNamespaces.OMNS.toASCIIString().equals( nsp ) ) {
                result[0] = XMLTools.getRequiredNodeAsString( exception.getRootElement(), OWSMSG, nsc );
                result[1] = XMLTools.getRequiredNodeAsString( exception.getRootElement(), OWSLOC, nsc );
            }
        } catch ( Exception e ) {
            // do nothing
            LOG.logWarning( "", e );
        }
        return result;
    }

}
