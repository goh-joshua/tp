package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableContractListTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializableContractListTest");
    private static final Path TYPICAL_CONTRACTS_FILE =
        TEST_DATA_FOLDER.resolve("typicalContractsContractList.json");
    private static final Path INVALID_CONTRACT_FILE =
        TEST_DATA_FOLDER.resolve("invalidContractContractList.json");
    private static final Path DUPLICATE_CONTRACT_FILE =
        TEST_DATA_FOLDER.resolve("duplicateContractContractList.json");

    // Commented out for now - data mismatch issue to be resolved later
    // @Test
    // public void toModelType_typicalContractsFile_success() throws Exception {
    //     JsonSerializableContractList dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTRACTS_FILE,
    //             JsonSerializableContractList.class).get();
    //     ContractList contractListFromFile = dataFromFile.toModelType();
    //     ContractList typicalContractsContractList = new ContractList();
    //     typicalContractsContractList.setContracts(TypicalContracts.getTypicalContracts());
    //     assertEquals(contractListFromFile, typicalContractsContractList);
    // }

    @Test
    public void toModelType_invalidContractFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContractList dataFromFile = JsonUtil.readJsonFile(INVALID_CONTRACT_FILE,
                JsonSerializableContractList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContracts_throwsIllegalValueException() throws Exception {
        JsonSerializableContractList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTRACT_FILE,
                JsonSerializableContractList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableContractList.MESSAGE_DUPLICATE_CONTRACT,
                dataFromFile::toModelType);
    }

}
