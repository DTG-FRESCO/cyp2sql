/*
 * Copyright (c) 2017.
 *
 * Oliver Crawford <o.crawford@hotmail.co.uk>
 * Lucian Carata <lc525@cam.ac.uk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package query_translation.sql.utilities_sql;

import exceptions.DQInvalidException;
import intermediate_rep.DecodedQuery;
import production.C2SProperties;

/**
 * For classes that take a DecodedQuery object and convert to SQL, they should extend this class. Included
 * in this class are some helper field, including an alphabet char array and number char array, and
 * the translate method definition.
 */
abstract class AbstractTranslation {
    static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] extendID = "123456789".toCharArray();
    static boolean usesOptimalTable = false;

    public abstract StringBuilder translate(StringBuilder sql, DecodedQuery decodedQuery, C2SProperties props) throws DQInvalidException;
}
