package com.andrewexe.repositories;

import java.io.File;

public interface FileStoredRepository {
    void loadFromCsv(String filename);
    File saveToCsv(String filename);
}
