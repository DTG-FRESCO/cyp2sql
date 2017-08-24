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

package database;

import java.util.Arrays;
import java.util.List;

public class SchemaConstants {
    // notice leading space!
    public static List<String> DATATYPES =
            Arrays.asList(" TEXT[]", " BIGINT", " TEXT", " INT", " BOOLEAN", " REAL");

    // reserved keywords in SQL that therefore cannot be used as relation names!
    public static List<String> RESERVED_KW =
            Arrays.asList("group", "user");
}
