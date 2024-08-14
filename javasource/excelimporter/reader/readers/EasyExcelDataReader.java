package excelimporter.reader.readers;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mendix.replication.MendixReplicationException;
import excelimporter.reader.readers.ExcelRowProcessor.ExcelCellData;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

public class EasyExcelDataReader {

    public static long readData(File excelFile, int sheetIndex, int startRowIndex, ExcelRowProcessor rowProcessor,
            Predicate<String> isColumnUsed) {
        NoModelDataListener noModelDataListener = new NoModelDataListener(rowProcessor, isColumnUsed);
        EasyExcel.read(excelFile, noModelDataListener).sheet(sheetIndex).headRowNumber(startRowIndex).doRead();
        return rowProcessor.getRowCounter();
    }
}

class NoModelDataListener extends AnalysisEventListener<Map<Integer, Object>> {
    private ExcelRowProcessor rowProcessor;
    private Predicate<String> isColumnUsed;

    public NoModelDataListener(ExcelRowProcessor rowProcessor, Predicate<String> isColumnUsed) {
        this.rowProcessor = rowProcessor;
        this.isColumnUsed = isColumnUsed;
    }

    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        var cellData = new ArrayList<ExcelCellData>();

        data.entrySet().stream().filter(entry -> isColumnUsed.test(String.valueOf(entry.getKey()))).forEach(entry -> {
            cellData.add(new ExcelCellData(entry.getKey(), entry.getValue().toString(), entry.getValue().toString()));
        });

        var rowHolder = context.readRowHolder();

        try {
            int rowNow = rowHolder.getRowIndex().intValue();
            this.rowProcessor.processValues(cellData.toArray(new ExcelCellData[0]), rowNow,
                    context.readSheetHolder().getSheetNo().intValue());
        } catch (MendixReplicationException e) {
            throw new ExcelRuntimeException(e);
        } finally {

        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            this.rowProcessor.finish();
            if (this.rowProcessor.getRowCounter() == 0)
                ExcelReader.logNode.warn(
                        "Excel Importer could not import any rows. Please check if the template is configured correctly. If the file was not created with Microsoft Excel for desktop, try opening the file with Excel and saving it with the same name before importing.");
            else
                ExcelReader.logNode
                        .info("Excel Importer successfully imported " + this.rowProcessor.getRowCounter() + " rows");
        } catch (MendixReplicationException e) {
            throw new ExcelRuntimeException(e); // needed for backward compatibility
        }
    }
}
