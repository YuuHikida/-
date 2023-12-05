package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import analix.DHIT.model.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("SELECT report_id FROM report WHERE employee_code = #{employeeCode} and date= #{date}")
    String selectIdByEmployeeCodeAndDate(int employeeCode, LocalDate date);

    @Select("SELECT * FROM report WHERE report_id = #{reportId}")
    Report SelectById(int reportId);

    @Select("SELECT report_id FROM report WHERE employee_code = (SELECT employee_code FROM report WHERE report_id = #{reportId}) AND date < (SELECT date FROM report WHERE report_id = #{reportId}) ORDER BY date DESC LIMIT 1")
    String selectBeforeIdById(int reportId);

    @Select("SELECT report_id FROM report WHERE employee_code = (SELECT employee_code FROM report WHERE report_id = #{reportId}) AND date > (SELECT date FROM report WHERE report_id = #{reportId}) ORDER BY date LIMIT 1")
    String selectAfterIdById(int reportId);

    @Select("SELECT report_id FROM report WHERE employee_code = #{employeeCode} ORDER BY date DESC LIMIT 1")
    String selectLatestIdByEmployeeCode(int employeeCode);

    //↓@Optionsは自動生成された"id"を返す処理をする
    @Insert("INSERT INTO report(employee_code, `condition`, impressions, tomorrow_schedule, date, start_time, end_time, is_lateness, lateness_reason, is_left_early) " +
            "VALUES(#{employeeCode}, #{condition}, #{impressions}, #{tomorrowSchedule}, #{date}, #{startTime}, #{endTime}, #{isLateness}, #{latenessReason}, #{isLeftEarly})")
    @Options(useGeneratedKeys = true, keyProperty = "report_id")
    void insertReport(Report report);

    //↓COUNT(*)は集計関数していされた行の数を返す
    @Select("SELECT COUNT(*) FROM report WHERE employee_code = #{employeeCode} AND date = #{date}")
    int countReportByEmployeeCodeAndDate(int employeeCode, LocalDate date);

    @Delete("DELETE FROM report WHERE report_id = #{reportId}")
    void deleteById(int reportId);

    @Update("UPDATE report SET " +
            "`condition` = #{condition}, " +
            "impressions = #{impressions}, " +
            "tomorrow_schedule = #{tomorrowSchedule}, " +
            "start_time = #{startTime}, " +
            "end_time = #{endTime}, " +
            "is_lateness = #{isLateness}, " +
            "lateness_reason = #{latenessReason}, " +
            "is_left_early = #{isLeftEarly} " +
            "WHERE report_ = #{report_id}")
    void updateReport(Report report);

    //reportテーブルのemployeeCodeに紐づいているIdを全取得
    @Select("SELECT report_id FROM report WHERE employee_code=#{employeeCode}")
    List<Integer> selectIdsByEmployeeCode(int employeeCode);


    //追記*****************************************************
    //報告一覧表示----------------------------------
    //引数として受け取ったemployeeCodeと一致するデータを全件取得するSELECT文
    @Select("SELECT * FROM report WHERE employee_code=#{employeeCode}")
    List<Report> selectAll(int employeeCode);
    //検索条件-------------------------------------



}
