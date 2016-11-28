package bg.tilchev.Pr04AddMinion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Pr04AddMinion {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] minionInfo = reader.readLine().split(" ");
        String newMinionName = minionInfo[1];
        int newMinionAge = Integer.parseInt(minionInfo[2]);
        String newMinionTown = minionInfo[3];
        String newMinionVillain = reader.readLine().split(" ")[1];
        try(Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            if (!townExistsInDb(connection, newMinionTown)) {
                String resultMsg = insertTownInDb(connection, newMinionTown);
                System.out.println(resultMsg);
            }
            if (!villainExistsInDb(connection, newMinionVillain)) {
                String resultMsg = insertVillainInDb(connection, newMinionVillain);
                System.out.println(resultMsg);
            }
            int villainId = getVillainId(connection, newMinionVillain);
            int townId = getTownId(connection, newMinionTown);
            String resultMsg = insertNewMinionInDb(connection, newMinionName, newMinionAge, townId, villainId, newMinionVillain);
            System.out.println(resultMsg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String insertNewMinionInDb(Connection connection,
                                            String newMinionName,
                                            int newMinionAge,
                                            int townId,
                                            int villainId,
                                            String villainNmae) {
        String rawSQL = "INSERT INTO minions (name, age, town_id) VALUES\n" +
                        "(?, ?, ?);";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement(rawSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newMinionName);
            statement.setInt(2, newMinionAge);
            statement.setInt(3, townId);
            statement.execute();
            result = statement.getGeneratedKeys();
            result.next();
            int newId = result.getInt(1);
            rawSQL = "INSERT INTO villains_minions VALUES\n" +
                     "(?, ?);";
            statement = connection.prepareStatement(rawSQL);
            statement.setInt(1, newId);
            statement.setInt(2, villainId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.format("Successfully added %s to be minion of %s.", newMinionName, villainNmae);
    }

    private static int getTownId(Connection connection, String newMinionTown) {
        String rawSQL = "SELECT t.id FROM towns AS t\n" +
                        " WHERE t.name = ?;";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionTown);
            result = statement.executeQuery();
            result.next();
            return result.getInt("t.id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    private static int getVillainId(Connection connection, String newMinionVillain) {
        String rawSQL = "SELECT v.id FROM villains AS v\n" +
                        " WHERE v.name = ?;";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionVillain);
            result = statement.executeQuery();
            result.next();
            return result.getInt("v.id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    private static String insertVillainInDb(Connection connection, String newMinionVillain) {
        String rawSQL = "INSERT INTO villains (name, evilness_factor) VALUES\n" +
                        "(?, 'evil');";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionVillain);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.format("Villain %s was added to the database.", newMinionVillain);
    }

    private static boolean villainExistsInDb(Connection connection, String newMinionVillain) {
        String rawSQL = "SELECT v.name FROM villains AS v\n" +
                        " WHERE v.name = ?;";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionVillain);
            result = statement.executeQuery();
            if (result.isBeforeFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private static String insertTownInDb(Connection connection, String newMinionTown) {
        String rawSQL = "INSERT INTO towns (name) VALUES\n" +
                        "(?);";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionTown);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.format("Town %s was added to the database.", newMinionTown);
    }

    private static boolean townExistsInDb(Connection connection, String newMinionTown) {
        String rawSQL = "SELECT t.name FROM towns AS t\n" +
                        " WHERE t.name = ?;";
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, newMinionTown);
            result = statement.executeQuery();
            if (result.isBeforeFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
