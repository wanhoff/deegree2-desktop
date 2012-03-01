//$HeadURL: svn+ssh://lbuesching@svn.wald.intevation.de/deegree/base/trunk/resources/eclipse/files_template.xml $
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2012 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

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
package org.deegree.igeo.dataadapter.jdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.deegree.framework.util.Pair;
import org.deegree.igeo.views.swing.util.panels.PanelDialog;
import org.deegree.io.DBConnectionPool;
import org.deegree.io.DBPoolException;

public class JdbcConnectionParameterCache {

    // driverToUrlToLogin
    private static Map<String, Map<String, Pair<String, String>>> cache = new HashMap<String, Map<String, Pair<String, String>>>();

    private static JdbcConnectionParameterCache connCache;
    
    private JdbcConnectionParameterCache(){
        
    }
    
    public static JdbcConnectionParameterCache getInstance(){
        if (connCache == null){
            connCache = new JdbcConnectionParameterCache();
        }
        return connCache;
    }
    
    public JdbcConnectionParameter getJdbcConnectionParameter( String driver, String url, String user,
                                                                      String passwd ) {
        if ( !cache.containsKey( driver ) ) {
            cache.put( driver, new HashMap<String, Pair<String, String>>() );
        }
        Map<String, Pair<String, String>> urlToLogin = cache.get( driver );
        if ( !urlToLogin.containsKey( url ) ) {
            String u = user;
            String p = passwd;
            if ( u == null && p == null ) {
                Pair<String, String> askLoginParameter = askLoginParameter( driver, url, user, passwd, true );
                u = askLoginParameter.first;
                p = askLoginParameter.second;
            }
            urlToLogin.put( url, new Pair<String, String>( u, p ) );
        }
        Pair<String, String> pair = urlToLogin.get( url );
        return new JdbcConnectionParameter( driver, url, pair.first, pair.second );
    }

    private Pair<String, String> askLoginParameter( String driver, String url, String user, String passwd,
                                                           boolean isFirstAttempt ) {
        LoginPanel panel = new LoginPanel( driver, url, user, passwd, isFirstAttempt );
        PanelDialog pd = new PanelDialog( panel, true );
        pd.setVisible( true );
        String pw = null;
        String u = null;
        if ( pd.clickedOk ) {
            u = panel.getUser();
            pw = panel.getPasswd();
        } else {
            return new Pair<String, String>();
        }
        DBConnectionPool pool = DBConnectionPool.getInstance();
        Connection conn = null;
        try {
            conn = pool.acquireConnection( driver, url, u, pw );
        } catch ( Exception e ) {
            // try again
        }
        if ( conn == null ) {
            return askLoginParameter( driver, url, u, pw, false );
        } else {
            try {
                pool.releaseConnection( conn, driver, url, u, pw );
            } catch ( DBPoolException e ) {
                // try again
            }
        }
        return new Pair<String, String>( u, pw );
    }
}