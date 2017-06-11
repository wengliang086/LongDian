package com.longdian.fragment.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.ScrollViewListener;
import com.longdian.R;
import com.longdian.fragment.weather.model.WeatherDataAll;
import com.longdian.fragment.weather.model.WeatherDaysForecast;
import com.longdian.fragment.weather.model.WeatherInfo;
import com.longdian.fragment.weather.model.WeatherLifeIndex;
import com.longdian.service.HoolaiException;
import com.longdian.service.HoolaiHttpMethods;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.util.LogUtil;
import com.longdian.util.MyUtil;
import com.longdian.util.ToastUtils;
import com.longdian.util.WeacConstants;
import com.longdian.view.LineChartViewDouble;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherOverviewFragment extends Fragment implements View.OnClickListener {

    /**
     * 城市名
     */
    private TextView mCityNameTv;

    /**
     * 警报
     */
    private TextView mAlarmTv;

    /**
     * 更新时间
     */
    private TextView mUpdateTimeTv;


    /**
     * 温度1
     */
    private ImageView mTemperature1Iv;

    /**
     * 温度2
     */
    private ImageView mTemperature2Iv;

    /**
     * 温度3
     */
    private ImageView mTemperature3Iv;

    /**
     * 天气类型
     */
    private TextView mWeatherTypeTv;


    /**
     * 大气环境
     */
    private TextView mAqiTv;

    /**
     * 湿度
     */
    private TextView mHumidityTv;

    /**
     * 风向、风力
     */
    private TextView mWindTv;


    /**
     * 今天天气类型图片
     */
    private ImageView mWeatherTypeIvToday;

    /**
     * 今天高温
     */
    private TextView mTempHighTvToday;

    /**
     * 今天低温
     */
    private TextView mTempLowTvToday;

    /**
     * 今天天气类型文字
     */
    private TextView mWeatherTypeTvToday;


    /**
     * 明天天气类型图片
     */
    private ImageView mWeatherTypeIvTomorrow;

    /**
     * 明天高温
     */
    private TextView mTempHighTvTomorrow;

    /**
     * 明天低温
     */
    private TextView mTempLowTvTomorrow;

    /**
     * 明天天气类型文字
     */
    private TextView mWeatherTypeTvTomorrow;


    /**
     * 后天天气类型图片
     */
    private ImageView mWeatherTypeIvDayAfterTomorrow;

    /**
     * 后天高温
     */
    private TextView mTempHighTvDayAfterTomorrow;

    /**
     * 后天低温
     */
    private TextView mTempLowTvDayAfterTomorrow;

    /**
     * 后天天气类型文字
     */
    private TextView mWeatherTypeTvDayAfterTomorrow;


    /**
     * 多天预报标题1
     */
    private TextView mDaysForecastTvWeek1;

    /**
     * 多天预报标题2
     */
    private TextView mDaysForecastTvWeek2;

    /**
     * 多天预报标题3
     */
    private TextView mDaysForecastTvWeek3;

    /**
     * 多天预报标题4
     */
    private TextView mDaysForecastTvWeek4;

    /**
     * 多天预报标题5
     */
    private TextView mDaysForecastTvWeek5;

    /**
     * 多天预报标题6
     */
    private TextView mDaysForecastTvWeek6;


    /**
     * 多天预报日期1
     */
    private TextView mDaysForecastTvDay1;

    /**
     * 多天预报日期2
     */
    private TextView mDaysForecastTvDay2;

    /**
     * 多天预报日期3
     */
    private TextView mDaysForecastTvDay3;

    /**
     * 多天预报日期4
     */
    private TextView mDaysForecastTvDay4;

    /**
     * 多天预报日期5
     */
    private TextView mDaysForecastTvDay5;

    /**
     * 多天预报日期6
     */
    private TextView mDaysForecastTvDay6;


    /**
     * 多天预报白天天气类型图片1
     */
    private ImageView mDaysForecastWeaTypeDayIv1;

    /**
     * 多天预报白天天气类型图片2
     */
    private ImageView mDaysForecastWeaTypeDayIv2;

    /**
     * 多天预报白天天气类型图片3
     */
    private ImageView mDaysForecastWeaTypeDayIv3;

    /**
     * 多天预报白天天气类型图片4
     */
    private ImageView mDaysForecastWeaTypeDayIv4;

    /**
     * 多天预报白天天气类型图片5
     */
    private ImageView mDaysForecastWeaTypeDayIv5;

    /**
     * 多天预报白天天气类型图片6
     */
    private ImageView mDaysForecastWeaTypeDayIv6;


    /**
     * 多天预报白天天气类型文字1
     */
    private TextView mDaysForecastWeaTypeDayTv1;

    /**
     * 多天预报白天天气类型文字2
     */
    private TextView mDaysForecastWeaTypeDayTv2;

    /**
     * 多天预报白天天气类型文字3
     */
    private TextView mDaysForecastWeaTypeDayTv3;

    /**
     * 多天预报白天天气类型文字4
     */
    private TextView mDaysForecastWeaTypeDayTv4;

    /**
     * 多天预报白天天气类型文字5
     */
    private TextView mDaysForecastWeaTypeDayTv5;

    /**
     * 多天预报白天天气类型文字6
     */
    private TextView mDaysForecastWeaTypeDayTv6;

    /**
     * 温度曲线
     */
    private LineChartViewDouble mCharView;

    /**
     * 多天预报夜间天气类型图片1
     */
    private ImageView mDaysForecastWeaTypeNightIv1;

    /**
     * 多天预报夜间天气类型图片2
     */
    private ImageView mDaysForecastWeaTypeNightIv2;

    /**
     * 多天预报夜间天气类型图片3
     */
    private ImageView mDaysForecastWeaTypeNightIv3;

    /**
     * 多天预报夜间天气类型图片4
     */
    private ImageView mDaysForecastWeaTypeNightIv4;

    /**
     * 多天预报夜间天气类型图片5
     */
    private ImageView mDaysForecastWeaTypeNightIv5;

    /**
     * 多天预报夜间天气类型图片6
     */
    private ImageView mDaysForecastWeaTypeNightIv6;


    /**
     * 多天预报夜间天气类型文字1
     */
    private TextView mDaysForecastWeaTypeNightTv1;

    /**
     * 多天预报夜间天气类型文字2
     */
    private TextView mDaysForecastWeaTypeNightTv2;

    /**
     * 多天预报夜间天气类型文字3
     */
    private TextView mDaysForecastWeaTypeNightTv3;

    /**
     * 多天预报夜间天气类型文字4
     */
    private TextView mDaysForecastWeaTypeNightTv4;

    /**
     * 多天预报夜间天气类型文字5
     */
    private TextView mDaysForecastWeaTypeNightTv5;

    /**
     * 多天预报夜间天气类型文字6
     */
    private TextView mDaysForecastWeaTypeNightTv6;


    /**
     * 多天预报风向1
     */
    private TextView mDaysForecastWindDirectionTv1;

    /**
     * 多天预报风向2
     */
    private TextView mDaysForecastWindDirectionTv2;

    /**
     * 多天预报风向3
     */
    private TextView mDaysForecastWindDirectionTv3;

    /**
     * 多天预报风向4
     */
    private TextView mDaysForecastWindDirectionTv4;

    /**
     * 多天预报风向5
     */
    private TextView mDaysForecastWindDirectionTv5;

    /**
     * 多天预报风向6
     */
    private TextView mDaysForecastWindDirectionTv6;


    /**
     * 多天预报风力1
     */
    private TextView mDaysForecastWindPowerTv1;

    /**
     * 多天预报风力2
     */
    private TextView mDaysForecastWindPowerTv2;

    /**
     * 多天预报风力3
     */
    private TextView mDaysForecastWindPowerTv3;

    /**
     * 多天预报风力4
     */
    private TextView mDaysForecastWindPowerTv4;

    /**
     * 多天预报风力5
     */
    private TextView mDaysForecastWindPowerTv5;

    /**
     * 多天预报风力6
     */
    private TextView mDaysForecastWindPowerTv6;

    /**
     * 雨伞指数TextView
     */
    private TextView mLifeIndexUmbrellaTv;

    /**
     * 紫外线指数TextView
     */
    private TextView mLifeIndexUltravioletRaysTv;

    /**
     * 穿衣指数TextView
     */
    private TextView mLifeIndexDressTv;

    /**
     * 感冒指数TextView
     */
    private TextView mLifeIndexColdTv;

    /**
     * 晨练指数TextView
     */
    private TextView mLifeIndexMorningExerciseTv;

    /**
     * 运动指数TextView
     */
    private TextView mLifeIndexSportTv;

    /**
     * 洗车指数TextView
     */
    private TextView mLifeIndexCarWashTv;

    /**
     * 晾晒指数TextView
     */
    private TextView mLifeIndexAirCureTv;

    /**
     * 下拉刷新ScrollView
     */
    public PullToRefreshScrollView mPullRefreshScrollView;

    /**
     * 刷新按钮
     */
    public ImageView mRefreshBtn;

    /**
     * 延迟刷新线程是否已经启动
     */
    public boolean mIsPostDelayed;

    /**
     * 延迟刷新Handler
     */
    public Handler mHandler;

    /**
     * 延迟刷新Runnable
     */
    public Runnable mRun;

    /**
     * 设置壁纸
     */
    private LinearLayout mBackGround;

    /**
     * 模糊处理过的Drawable
     */
    private Drawable mBlurDrawable;

    /**
     * 屏幕密度
     */
    private float mDensity;

    /**
     * 透明
     */
    private int mAlpha = 0;

    /**
     * 天气界面布局
     */
    private ViewGroup mWeatherGroup;

    /**
     * 加载中进度框
     */
    private ViewGroup mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fm_wea, container, false);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewstub_wea);
        viewStub.inflate();

        init(view);

        // 初始化天气
//        refreshWeather();
        showWeatherLayout();
        // 自动下拉刷新
        pullToRefresh();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 刷新按钮
            case R.id.action_refresh:
                Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                // 匀速
                LinearInterpolator lin = new LinearInterpolator();
                // 设置速率
                operatingAnim.setInterpolator(lin);
                mRefreshBtn.startAnimation(operatingAnim);
                refreshWeather();
                break;
            // 城市管理按钮
            case R.id.action_home:
                break;
        }
    }

    /**
     * 初始化天气
     *
     * @param weatherInfo 天气信息类
     */
    @SuppressWarnings("deprecation")
    private void initWeather(final WeatherInfo weatherInfo) {
        if (weatherInfo == null) {
            return;
        }
        // 多天预报信息
        List<WeatherDaysForecast> weatherDaysForecasts = weatherInfo.getWeatherDaysForecast();
/*        if (weatherDaysForecasts.size() < 6) {
            ToastUtil.showShortToast(getActivity(), getString(R.string.no_city_weather_info));
            return;
        }*/

        // 昨天天气信息（23：45开始到05：20以前的数据的日期和周）
        WeatherDaysForecast weather;
        // 昨天天气信息
        WeatherDaysForecast weather1;
        // 今天天气信息
        WeatherDaysForecast weather2;
        // 明天天气信息
        WeatherDaysForecast weather3;
        // 后天天气信息
        WeatherDaysForecast weather4;
        // 第五天天天气信息
        WeatherDaysForecast weather5;
        // 第六天天气信息
        WeatherDaysForecast weather6;

        String time[] = weatherInfo.getUpdateTime().split(":");
        int hour1 = Integer.parseInt(time[0]);
        int minute1 = Integer.parseInt(time[1]);
        //更新时间从23：45开始到05：20以前的数据，后移一天填充
        if ((hour1 == 23 && minute1 >= 45) || (hour1 < 5) ||
                ((hour1 == 5) && (minute1 < 20))) {
            if (weatherDaysForecasts.size() >= 6) {
                weather = weatherDaysForecasts.get(0);
                weather1 = weatherDaysForecasts.get(1);
                weather2 = weatherDaysForecasts.get(2);
                weather3 = weatherDaysForecasts.get(3);
                weather4 = weatherDaysForecasts.get(4);
                weather5 = weatherDaysForecasts.get(5);
                weather6 = weatherDaysForecasts.get(5);
            } else {
                weather = null;
                weather1 = weatherDaysForecasts.get(0);
                weather2 = weatherDaysForecasts.get(1);
                weather3 = weatherDaysForecasts.get(2);
                weather4 = weatherDaysForecasts.get(3);
                weather5 = weatherDaysForecasts.get(4);
                weather6 = weatherDaysForecasts.get(4);

            }
        } else {
            if (weatherDaysForecasts.size() >= 6) {
                weather = weatherDaysForecasts.get(0);
                weather1 = weatherDaysForecasts.get(0);
                weather2 = weatherDaysForecasts.get(1);
                weather3 = weatherDaysForecasts.get(2);
                weather4 = weatherDaysForecasts.get(3);
                weather5 = weatherDaysForecasts.get(4);
                weather6 = weatherDaysForecasts.get(5);
            } else {
                weather = null;
                weather1 = null;
                weather2 = weatherDaysForecasts.get(0);
                weather3 = weatherDaysForecasts.get(1);
                weather4 = weatherDaysForecasts.get(2);
                weather5 = weatherDaysForecasts.get(3);
                weather6 = weatherDaysForecasts.get(4);
            }
        }

        Calendar calendar = Calendar.getInstance();
        // 现在小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // 设置城市名
        setCityName(weatherInfo);
        // 设置预警信息
        setAlarmInfo(weatherInfo);
        // 设置更新时间
        setUpdateTime(weatherInfo);
        // 设置温度
        setTemperature(weatherInfo);
        // 设置天气类型
        setWeatherType(weather2, hour);
        // 设置aqi
        setAQI(weatherInfo);
        // 设置湿度
        setHumidity(weatherInfo);
        // 设置风向、风力
        setWind(weatherInfo);
        // 设置今天，明天，后天大概天气
        setThreeDaysWeather(weather2, weather3, weather4, hour);

        // 设置多天天气预报
        setDaysForecast(weather, weather1, weather2, weather3, weather4, weather5, weather6,
                hour1, minute1, calendar);

        // 生活指数信息
        setLifeIndex(weatherInfo);
    }

    /**
     * 初始化控件
     *
     * @param view view
     */
    private void init(View view) {
        mWeatherGroup = (ViewGroup) view.findViewById(R.id.weather_layout);
        mProgressBar = (ViewGroup) view.findViewById(R.id.progress_bar_llyt);

        mRefreshBtn = (ImageView) view.findViewById(R.id.action_refresh);
        mRefreshBtn.setOnClickListener(this);
        // HOME按钮
        ImageView homeBtn = (ImageView) view.findViewById(R.id.action_home);
        homeBtn.setOnClickListener(this);

        mCityNameTv = (TextView) view.findViewById(R.id.action_title);
        mAlarmTv = (TextView) view.findViewById(R.id.alarm);
        mUpdateTimeTv = (TextView) view.findViewById(R.id.update_time);

        mTemperature1Iv = (ImageView) view.findViewById(R.id.temperature1);
        mTemperature2Iv = (ImageView) view.findViewById(R.id.temperature2);
        mTemperature3Iv = (ImageView) view.findViewById(R.id.temperature3);
        mWeatherTypeTv = (TextView) view.findViewById(R.id.weather_type);

        mAqiTv = (TextView) view.findViewById(R.id.aqi);
        mHumidityTv = (TextView) view.findViewById(R.id.humidity);
        mWindTv = (TextView) view.findViewById(R.id.wind);

        mWeatherTypeIvToday = (ImageView) view.findViewById(R.id.weather_type_iv_today);
        mWeatherTypeIvTomorrow = (ImageView) view.findViewById(R.id.weather_type_iv_tomorrow);
        mWeatherTypeIvDayAfterTomorrow = (ImageView) view.findViewById(R.id.weather_type_iv_day_after_tomorrow);

        mTempHighTvToday = (TextView) view.findViewById(R.id.temp_high_today);
        mTempHighTvTomorrow = (TextView) view.findViewById(R.id.temp_high_tomorrow);
        mTempHighTvDayAfterTomorrow = (TextView) view.findViewById(R.id.temp_high_day_after_tomorrow);

        mTempLowTvToday = (TextView) view.findViewById(R.id.temp_low_today);
        mTempLowTvTomorrow = (TextView) view.findViewById(R.id.temp_low_tomorrow);
        mTempLowTvDayAfterTomorrow = (TextView) view.findViewById(R.id.temp_low_day_after_tomorrow);

        mWeatherTypeTvToday = (TextView) view.findViewById(R.id.weather_type_tv_today);
        mWeatherTypeTvTomorrow = (TextView) view.findViewById(R.id.weather_type_tv_tomorrow);
        mWeatherTypeTvDayAfterTomorrow = (TextView) view.findViewById(R.id.weather_type_tv_day_after_tomorrow);

        mDaysForecastTvWeek1 = (TextView) view.findViewById(R.id.wea_days_forecast_week1);
        mDaysForecastTvWeek2 = (TextView) view.findViewById(R.id.wea_days_forecast_week2);
        mDaysForecastTvWeek3 = (TextView) view.findViewById(R.id.wea_days_forecast_week3);
        mDaysForecastTvWeek4 = (TextView) view.findViewById(R.id.wea_days_forecast_week4);
        mDaysForecastTvWeek5 = (TextView) view.findViewById(R.id.wea_days_forecast_week5);
        mDaysForecastTvWeek6 = (TextView) view.findViewById(R.id.wea_days_forecast_week6);

        mDaysForecastTvDay1 = (TextView) view.findViewById(R.id.wea_days_forecast_day1);
        mDaysForecastTvDay2 = (TextView) view.findViewById(R.id.wea_days_forecast_day2);
        mDaysForecastTvDay3 = (TextView) view.findViewById(R.id.wea_days_forecast_day3);
        mDaysForecastTvDay4 = (TextView) view.findViewById(R.id.wea_days_forecast_day4);
        mDaysForecastTvDay5 = (TextView) view.findViewById(R.id.wea_days_forecast_day5);
        mDaysForecastTvDay6 = (TextView) view.findViewById(R.id.wea_days_forecast_day6);

        mDaysForecastWeaTypeDayIv1 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv1);
        mDaysForecastWeaTypeDayIv2 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv2);
        mDaysForecastWeaTypeDayIv3 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv3);
        mDaysForecastWeaTypeDayIv4 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv4);
        mDaysForecastWeaTypeDayIv5 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv5);
        mDaysForecastWeaTypeDayIv6 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_day_iv6);

        mDaysForecastWeaTypeDayTv1 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv1);
        mDaysForecastWeaTypeDayTv2 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv2);
        mDaysForecastWeaTypeDayTv3 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv3);
        mDaysForecastWeaTypeDayTv4 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv4);
        mDaysForecastWeaTypeDayTv5 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv5);
        mDaysForecastWeaTypeDayTv6 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_day_tv6);

        mCharView = (LineChartViewDouble) view.findViewById(R.id.line_char);

        mDaysForecastWeaTypeNightIv1 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv1);
        mDaysForecastWeaTypeNightIv2 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv2);
        mDaysForecastWeaTypeNightIv3 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv3);
        mDaysForecastWeaTypeNightIv4 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv4);
        mDaysForecastWeaTypeNightIv5 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv5);
        mDaysForecastWeaTypeNightIv6 = (ImageView) view.findViewById(R.id.wea_days_forecast_weather_night_iv6);

        mDaysForecastWeaTypeNightTv1 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv1);
        mDaysForecastWeaTypeNightTv2 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv2);
        mDaysForecastWeaTypeNightTv3 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv3);
        mDaysForecastWeaTypeNightTv4 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv4);
        mDaysForecastWeaTypeNightTv5 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv5);
        mDaysForecastWeaTypeNightTv6 = (TextView) view.findViewById(R.id.wea_days_forecast_weather_night_tv6);

        mDaysForecastWindDirectionTv1 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv1);
        mDaysForecastWindDirectionTv2 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv2);
        mDaysForecastWindDirectionTv3 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv3);
        mDaysForecastWindDirectionTv4 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv4);
        mDaysForecastWindDirectionTv5 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv5);
        mDaysForecastWindDirectionTv6 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_direction_tv6);

        mDaysForecastWindPowerTv1 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv1);
        mDaysForecastWindPowerTv2 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv2);
        mDaysForecastWindPowerTv3 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv3);
        mDaysForecastWindPowerTv4 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv4);
        mDaysForecastWindPowerTv5 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv5);
        mDaysForecastWindPowerTv6 = (TextView) view.findViewById(R.id.wea_days_forecast_wind_power_tv6);

        mLifeIndexUmbrellaTv = (TextView) view.findViewById(R.id.wea_life_index_tv_umbrella);
        mLifeIndexUltravioletRaysTv = (TextView) view.findViewById(R.id.wea_life_index_tv_ultraviolet_rays);
        mLifeIndexDressTv = (TextView) view.findViewById(R.id.wea_life_tv_index_dress);
        mLifeIndexColdTv = (TextView) view.findViewById(R.id.wea_life_index_tv_cold);
        mLifeIndexMorningExerciseTv = (TextView) view.findViewById(R.id.wea_life_index_tv_morning_exercise);
        mLifeIndexSportTv = (TextView) view.findViewById(R.id.wea_life_index_tv_sport);
        mLifeIndexCarWashTv = (TextView) view.findViewById(R.id.wea_life_index_tv_car_wash);
        mLifeIndexAirCureTv = (TextView) view.findViewById(R.id.wea_life_index_tv_air_cure);

        // 雨伞指数控件
        RelativeLayout lifeIndexUmbrellaRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_umbrella);
        // 紫外线指数控件
        RelativeLayout lifeIndexUltravioletRaysRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_ultraviolet_rays);
        // 穿衣指数控件
        RelativeLayout lifeIndexDressRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_dress);
        // 感冒指数控件
        RelativeLayout lifeIndexColdRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_cold);
        // 晨练指数控件
        RelativeLayout lifeIndexMorningExerciseRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_morning_exercise);
        //  运动指数控件
        RelativeLayout lifeIndexSportRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_sport);
        // 洗车指数控件
        RelativeLayout lifeIndexCarWashRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_carwash);
        // 晾晒指数控件
        RelativeLayout lifeIndexAirCureRlyt = (RelativeLayout) view.findViewById(R.id.wea_life_index_rlyt_air_cure);

        lifeIndexUmbrellaRlyt.setOnClickListener(this);
        lifeIndexUltravioletRaysRlyt.setOnClickListener(this);
        lifeIndexDressRlyt.setOnClickListener(this);
        lifeIndexColdRlyt.setOnClickListener(this);
        lifeIndexMorningExerciseRlyt.setOnClickListener(this);
        lifeIndexSportRlyt.setOnClickListener(this);
        lifeIndexCarWashRlyt.setOnClickListener(this);
        lifeIndexAirCureRlyt.setOnClickListener(this);

        mDensity = getResources().getDisplayMetrics().density;
        mBlurDrawable = MyUtil.getWallPaperBlurDrawable(getActivity());
        mBackGround = (LinearLayout) view.findViewById(R.id.wea_background);

        mPullRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pull_refresh_scrollview);
        // 设置下拉刷新
        setPullToRefresh();
        mPullRefreshScrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
//                LogUtil.i(LOG_TAG, "x: " + x + "y: " + y + "oldx: " + oldx + "oldy: " + oldy);
                // scroll最大滚动距离（xxxh：2320）/密度（xxxh：3）/1.5  =  515
                mAlpha = Math.round(Math.round(y / mDensity / 1.5));
                if (mAlpha > 255) {
                    mAlpha = 255;
                } else if (mAlpha < 0) {
                    mAlpha = 0;
                }
                // 设置模糊处理后drawable的透明度
                mBlurDrawable.setAlpha(mAlpha);
                // 设置背景
                //noinspection deprecation
                mBackGround.setBackgroundDrawable(mBlurDrawable);
            }
        });
    }

    private void showWeatherLayout() {
        // 隐藏加载进度框
        mProgressBar.setVisibility(View.GONE);
        // 首次进入天气界面显示初始布局
        mWeatherGroup.setVisibility(View.VISIBLE);
    }

    /**
     * 设置下拉刷新
     */
    private void setPullToRefresh() {
        mPullRefreshScrollView.getLoadingLayoutProxy().setPullLabel(getString(R.string.pull_to_refresh));
        mPullRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel(getString(R.string.refreshing));
        mPullRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel(getString(R.string.leave_to_refresh));
        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

            @Override
            public void onRefresh(
                    PullToRefreshBase<ScrollView> refreshView) {
                refreshWeather();
            }
        });

    }

    /**
     * 下拉刷新
     */
    private void pullToRefresh() {
        mHandler = new Handler();
        mRun = new Runnable() {
            @Override
            public void run() {
                try {
                    mIsPostDelayed = false;
                    if (!getActivity().isFinishing()) {
                        mPullRefreshScrollView.setRefreshing();
                        // 加载成功
//                        mHasLoadedOnce = true;
                    }
                } catch (Exception e) {
//                    LogUtil.e(LOG_TAG, e.toString());
                }
            }
        };
        mHandler.postDelayed(mRun, 1000);
        mIsPostDelayed = true;
    }

    /**
     * 刷新天气
     */
    private void refreshWeather() {
        HoolaiHttpMethods.getInstance().weatherIndex(getActivity(), new ObserverOnNextAndErrorListener<WeatherDataAll>() {
            @Override
            public void onNext(WeatherDataAll weatherDataAll) {
                WeatherInfo weatherInfo = new WeatherInfo();


                try {
                    stopRefresh();
                    initWeather(weatherInfo);
                } catch (Exception e) {
                    LogUtil.e(e);
                }
            }

            @Override
            public void onError(HoolaiException e) {
                stopRefresh();
                ToastUtils.showToast(getActivity(), e.getMessage());
            }
        });
    }

    /**
     * 停止正在刷新动画
     */
    private void stopRefresh() {
        // 停止正在刷新动画
        mPullRefreshScrollView.onRefreshComplete();
        // 取消刷新按钮的动画
        mRefreshBtn.clearAnimation();
    }

    /**
     * 设置多天天气预报
     */
    private void setDaysForecast(WeatherDaysForecast weather, WeatherDaysForecast weather1,
                                 WeatherDaysForecast weather2, WeatherDaysForecast weather3,
                                 WeatherDaysForecast weather4, WeatherDaysForecast weather5,
                                 WeatherDaysForecast weather6, int hour1, int minute1,
                                 Calendar calendar) {
        // 日期和星期标题 【索引0：日期;索引1：星期】
        String[] day1;
        String[] day2;
        String[] day3;
        String[] day4;
        String[] day5;
        String[] day6;
        if ((hour1 == 23 && minute1 >= 45) || (hour1 < 5) || ((hour1 == 5) && (minute1 < 20))) {
            if (weather != null) {
                day1 = getDay(weather.getDate());
            } else {
                day1 = null;
            }

            assert weather1 != null;
            day2 = getDay(weather1.getDate());
            day3 = getDay(weather2.getDate());
            day4 = getDay(weather3.getDate());
            day5 = getDay(weather4.getDate());
            day6 = getDay(weather5.getDate());
        } else {
            if (weather1 != null) {
                day1 = getDay(weather1.getDate());
            } else {
                day1 = null;
            }

            day2 = getDay(weather2.getDate());
            day3 = getDay(weather3.getDate());
            day4 = getDay(weather4.getDate());
            day5 = getDay(weather5.getDate());
            day6 = getDay(weather6.getDate());
        }

        // 设置标题星期
        mDaysForecastTvWeek1.setText(getString(R.string.yesterday));
        mDaysForecastTvWeek2.setText(getString(R.string.today));
        mDaysForecastTvWeek3.setText(getWeek(day3[1]));
        mDaysForecastTvWeek4.setText(getWeek(day4[1]));
        mDaysForecastTvWeek5.setText(getWeek(day5[1]));
        mDaysForecastTvWeek6.setText(getWeek(day6[1]));

        // 月份
        calendar.add(Calendar.DATE, -1);
        String month1 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        calendar.add(Calendar.DATE, 1);
        String month2 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        calendar.add(Calendar.DATE, 1);
        String month3 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        calendar.add(Calendar.DATE, 1);
        String month4 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        calendar.add(Calendar.DATE, 1);
        String month5 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        calendar.add(Calendar.DATE, 1);
        String month6 = String.valueOf(calendar.get(Calendar.MONTH) + 1);

        // 日
        String day01;
        if (day1 != null) {
            day01 = day1[0].split("日")[0];
        } else {
            day01 = null;
        }

        String day02 = day2[0].split("日")[0];
        String day03 = day3[0].split("日")[0];
        String day04 = day4[0].split("日")[0];
        String day05 = day5[0].split("日")[0];
        String day06 = day6[0].split("日")[0];

        // 斜杠
        String date = getString(R.string.date);
        // 设置日期
        if (day01 != null) {
            mDaysForecastTvDay1.setText(String.format(date, month1, day01));
        } else {
            mDaysForecastTvDay1.setText(R.string.dash);
        }

        mDaysForecastTvDay2.setText(String.format(date, month2, day02));
        mDaysForecastTvDay3.setText(String.format(date, month3, day03));
        mDaysForecastTvDay4.setText(String.format(date, month4, day04));
        mDaysForecastTvDay5.setText(String.format(date, month5, day05));
        mDaysForecastTvDay6.setText(String.format(date, month6, day06));

        // 取得白天天气类型图片id
        int weatherDayId1;
        if (weather != null) {
            assert weather1 != null;
            weatherDayId1 = MyUtil.getWeatherTypeImageID(weather1.getTypeDay(), true);
        } else {
            weatherDayId1 = R.drawable.ic_weather_no;
        }
        int weatherDayId2 = MyUtil.getWeatherTypeImageID(weather2.getTypeDay(), true);
        int weatherDayId3 = MyUtil.getWeatherTypeImageID(weather3.getTypeDay(), true);
        int weatherDayId4 = MyUtil.getWeatherTypeImageID(weather4.getTypeDay(), true);
        int weatherDayId5 = MyUtil.getWeatherTypeImageID(weather5.getTypeDay(), true);
        int weatherDayId6 = MyUtil.getWeatherTypeImageID(weather6.getTypeDay(), true);

        //设置白天天气类型图片
        mDaysForecastWeaTypeDayIv1.setImageResource(weatherDayId1);
        mDaysForecastWeaTypeDayIv2.setImageResource(weatherDayId2);
        mDaysForecastWeaTypeDayIv3.setImageResource(weatherDayId3);
        mDaysForecastWeaTypeDayIv4.setImageResource(weatherDayId4);
        mDaysForecastWeaTypeDayIv5.setImageResource(weatherDayId5);
        mDaysForecastWeaTypeDayIv6.setImageResource(weatherDayId6);

        // 设置白天天气类型文字
        if (weather != null) {
            mDaysForecastWeaTypeDayTv1.setText(weather1.getTypeDay());
        } else {
            mDaysForecastWeaTypeDayTv1.setText(R.string.dash);
        }

        mDaysForecastWeaTypeDayTv2.setText(weather2.getTypeDay());
        mDaysForecastWeaTypeDayTv3.setText(weather3.getTypeDay());
        mDaysForecastWeaTypeDayTv4.setText(weather4.getTypeDay());
        mDaysForecastWeaTypeDayTv5.setText(weather5.getTypeDay());
        mDaysForecastWeaTypeDayTv6.setText(weather6.getTypeDay());

        // 设置白天温度曲线
        if (weather != null) {
            mCharView.setTempDay(new int[]{getTemp(weather1.getHigh()),
                    getTemp(weather2.getHigh()), getTemp(weather3.getHigh()),
                    getTemp(weather4.getHigh()), getTemp(weather5.getHigh()),
                    getTemp(weather6.getHigh())});
        } else {
            mCharView.setTempDay(new int[]{-1000,
                    getTemp(weather2.getHigh()), getTemp(weather3.getHigh()),
                    getTemp(weather4.getHigh()), getTemp(weather5.getHigh()),
                    getTemp(weather6.getHigh())});
        }
        // 设置夜间温度曲线
        if (weather != null) {
            mCharView.setTempNight(new int[]{getTemp(weather1.getLow()),
                    getTemp(weather2.getLow()), getTemp(weather3.getLow()),
                    getTemp(weather4.getLow()), getTemp(weather5.getLow()),
                    getTemp(weather6.getLow())});
        } else {
            mCharView.setTempNight(new int[]{-1000,
                    getTemp(weather2.getLow()), getTemp(weather3.getLow()),
                    getTemp(weather4.getLow()), getTemp(weather5.getLow()),
                    getTemp(weather6.getLow())});
        }
        mCharView.invalidate();

        // 设置夜间天气类型文字
        if (weather != null) {
            mDaysForecastWeaTypeNightTv1.setText(weather1.getTypeNight());
        } else {
            mDaysForecastWeaTypeNightTv1.setText(R.string.dash);
        }
        mDaysForecastWeaTypeNightTv2.setText(weather2.getTypeNight());
        mDaysForecastWeaTypeNightTv3.setText(weather3.getTypeNight());
        mDaysForecastWeaTypeNightTv4.setText(weather4.getTypeNight());
        mDaysForecastWeaTypeNightTv5.setText(weather5.getTypeNight());
        mDaysForecastWeaTypeNightTv6.setText(weather6.getTypeNight());

        // 取得夜间天气类型图片id
        int weatherNightId1;
        if (weather != null) {
            weatherNightId1 = MyUtil.getWeatherTypeImageID(weather1.getTypeNight(), false);
        } else {
            weatherNightId1 = R.drawable.ic_weather_no;
        }
        int weatherNightId2 = MyUtil.getWeatherTypeImageID(weather2.getTypeNight(), false);
        int weatherNightId3 = MyUtil.getWeatherTypeImageID(weather3.getTypeNight(), false);
        int weatherNightId4 = MyUtil.getWeatherTypeImageID(weather4.getTypeNight(), false);
        int weatherNightId5 = MyUtil.getWeatherTypeImageID(weather5.getTypeNight(), false);
        int weatherNightId6 = MyUtil.getWeatherTypeImageID(weather6.getTypeNight(), false);

        //设置夜间天气类型图片
        mDaysForecastWeaTypeNightIv1.setImageResource(weatherNightId1);
        mDaysForecastWeaTypeNightIv2.setImageResource(weatherNightId2);
        mDaysForecastWeaTypeNightIv3.setImageResource(weatherNightId3);
        mDaysForecastWeaTypeNightIv4.setImageResource(weatherNightId4);
        mDaysForecastWeaTypeNightIv5.setImageResource(weatherNightId5);
        mDaysForecastWeaTypeNightIv6.setImageResource(weatherNightId6);

        // 设置风向
        if (weather != null) {
            mDaysForecastWindDirectionTv1.setText(weather1.getWindDirectionDay());
        } else {
            mDaysForecastWindDirectionTv1.setText(R.string.dash);
        }
        mDaysForecastWindDirectionTv2.setText(weather2.getWindDirectionDay());
        mDaysForecastWindDirectionTv3.setText(weather3.getWindDirectionDay());
        mDaysForecastWindDirectionTv4.setText(weather4.getWindDirectionDay());
        mDaysForecastWindDirectionTv5.setText(weather5.getWindDirectionDay());
        mDaysForecastWindDirectionTv6.setText(weather6.getWindDirectionDay());

        // 设置风力
        if (weather != null) {
            mDaysForecastWindPowerTv1.setText(weather1.getWindPowerDay());
        } else {
            mDaysForecastWindPowerTv1.setText(R.string.dash);
        }
        mDaysForecastWindPowerTv2.setText(weather2.getWindPowerDay());
        mDaysForecastWindPowerTv3.setText(weather3.getWindPowerDay());
        mDaysForecastWindPowerTv4.setText(weather4.getWindPowerDay());
        mDaysForecastWindPowerTv5.setText(weather5.getWindPowerDay());
        mDaysForecastWindPowerTv6.setText(weather6.getWindPowerDay());
    }

    /**
     * 生活指数信息
     */
    private void setLifeIndex(WeatherInfo weatherInfo) {
        List<WeatherLifeIndex> weatherLifeIndexes = weatherInfo.getWeatherLifeIndex();
        // 设置生活指数
        for (WeatherLifeIndex index : weatherLifeIndexes) {
            setLifeIndex(index);
        }
    }

    /**
     * 设置今天，明天，后天大概天气
     *
     * @param weather2 今天
     * @param weather3 明天
     * @param weather4 后天
     * @param hour     当前小时
     */
    private void setThreeDaysWeather(WeatherDaysForecast weather2, WeatherDaysForecast weather3,
                                     WeatherDaysForecast weather4, int hour) {
        // 天气类型图片id
        int weatherId;

        // 设置今天天气信息
        // 当前为凌晨
        if (hour >= 0 && hour < 6) {
            weatherId = MyUtil.getWeatherTypeImageID(weather2.getTypeDay(), false);
            // 当前为白天时
        } else if (hour >= 6 && hour < 18) {
            weatherId = MyUtil.getWeatherTypeImageID(weather2.getTypeDay(), true);
            // 当前为夜间
        } else {
            weatherId = MyUtil.getWeatherTypeImageID(weather2.getTypeNight(), false);
        }
        mWeatherTypeIvToday.setImageResource(weatherId);
        mTempHighTvToday.setText(weather2.getHigh().substring(3));
        mTempLowTvToday.setText(weather2.getLow().substring(3));
        mWeatherTypeTvToday.setText(MyUtil.getWeatherType
                (getActivity(), weather2.getTypeDay(), weather2.getTypeNight()));

        // 设置明天天气信息
        weatherId = MyUtil.getWeatherTypeImageID(weather3.getTypeDay(), true);
        mWeatherTypeIvTomorrow.setImageResource(weatherId);
        mTempHighTvTomorrow.setText(weather3.getHigh().substring(3));
        mTempLowTvTomorrow.setText(weather3.getLow().substring(3));
        mWeatherTypeTvTomorrow.setText(MyUtil.getWeatherType
                (getActivity(), weather3.getTypeDay(), weather3.getTypeNight()));

        // 设置后天天气信息
        weatherId = MyUtil.getWeatherTypeImageID(weather4.getTypeDay(), true);
        mWeatherTypeIvDayAfterTomorrow.setImageResource(weatherId);
        mTempHighTvDayAfterTomorrow.setText(weather4.getHigh().substring(3));
        mTempLowTvDayAfterTomorrow.setText(weather4.getLow().substring(3));
        mWeatherTypeTvDayAfterTomorrow.setText(MyUtil.getWeatherType
                (getActivity(), weather4.getTypeDay(), weather4.getTypeNight()));
    }

    /**
     * 设置风向、风力
     */
    private void setWind(WeatherInfo weatherInfo) {
        if (weatherInfo.getWindDirection() != null && weatherInfo.getWindPower() != null) {
            // 设置风向图片
            setImage(mWindTv, getWindImageId(weatherInfo.getWindDirection()));
            // 设置风向、风力
            mWindTv.setText(String.format(getString(R.string.aqi)
                    , weatherInfo.getWindDirection(), weatherInfo.getWindPower()));
        } else {
            setImage(mWindTv, R.drawable.ic_wind_3);
            mWindTv.setText(R.string.no);
        }
    }

    /**
     * 设置湿度
     */
    private void setHumidity(WeatherInfo weatherInfo) {
        if (weatherInfo.getHumidity() != null) {
            // 设置湿度图片
//            setImage(mHumidityTv, getHumidityImageId(weatherInfo.getHumidity()));
            // 设置湿度
            mHumidityTv.setText(String.format(getString(R.string.humidity),
                    weatherInfo.getHumidity()));
        } else {
//            setImage(mHumidityTv, R.drawable.ic_humidity20);
            mHumidityTv.setText(R.string.no);
        }
    }

    /**
     * 设置aqi
     */
    private void setAQI(WeatherInfo weatherInfo) {
        if (weatherInfo.getQuality() != null && weatherInfo.getAQI() != null) {
            mAqiTv.setVisibility(View.VISIBLE);
            // 设置空气质量图片
            setImage(mAqiTv, getQualityImageId(weatherInfo.getQuality()));
            // 设置空气质量
            mAqiTv.setText(String.format(getString(R.string.aqi),
                    weatherInfo.getQuality(), weatherInfo.getAQI()));
        } else {
            mAqiTv.setVisibility(View.GONE);
        }
    }

    /**
     * 设置天气类型
     *
     * @param weatherToday 今天天气信息
     * @param hour         当前小时
     */
    private void setWeatherType(WeatherDaysForecast weatherToday, int hour) {
        if (hour < 18) {
            // 白天天气
            mWeatherTypeTv.setText(weatherToday.getTypeDay());
        } else {
            // 夜间天气
            mWeatherTypeTv.setText(weatherToday.getTypeNight());
        }
    }

    /**
     * 设置温度
     *
     * @param weatherInfo weatherInfo
     */
    private void setTemperature(WeatherInfo weatherInfo) {
        String temp = weatherInfo.getTemperature();
        mTemperature1Iv.setVisibility(View.VISIBLE);
        mTemperature2Iv.setVisibility(View.VISIBLE);
        mTemperature3Iv.setVisibility(View.VISIBLE);
        if (temp != null) {
            // 两位正数
            if (temp.length() == 2 && !temp.contains("-")) {
                int temp1 = Integer.parseInt(temp.substring(0, 1));
                setTemperatureImage(temp1, mTemperature1Iv);
                int temp2 = Integer.parseInt(temp.substring(1));
                setTemperatureImage(temp2, mTemperature2Iv);
                mTemperature3Iv.setVisibility(View.GONE);
                // 一位
            } else if (temp.length() == 1 && !temp.contains("-")) {
                int temp1 = Integer.parseInt(temp);
                setTemperatureImage(temp1, mTemperature1Iv);
                mTemperature2Iv.setVisibility(View.GONE);
                mTemperature3Iv.setVisibility(View.GONE);
                // 两位负数
            } else if (temp.length() == 2 && temp.contains("-")) {
                mTemperature1Iv.setImageResource(R.drawable.ic_minus);
                int temp2 = Integer.parseInt(temp.substring(1));
                setTemperatureImage(temp2, mTemperature2Iv);
                mTemperature3Iv.setVisibility(View.GONE);
                // 三位负数
            } else if (temp.length() == 3 && temp.contains("-")) {
                mTemperature1Iv.setImageResource(R.drawable.ic_minus);
                int temp2 = Integer.parseInt(temp.substring(1, 2));
                setTemperatureImage(temp2, mTemperature2Iv);
                int temp3 = Integer.parseInt(temp.substring(2));
                setTemperatureImage(temp3, mTemperature3Iv);
            } else {
                mTemperature1Iv.setImageResource(R.drawable.number_0);
                mTemperature2Iv.setImageResource(R.drawable.number_0);
                mTemperature3Iv.setImageResource(R.drawable.number_0);
            }
        } else {
            mTemperature1Iv.setImageResource(R.drawable.number_0);
            mTemperature2Iv.setImageResource(R.drawable.number_0);
            mTemperature3Iv.setImageResource(R.drawable.number_0);
        }
    }

    /**
     * 设置更新时间
     *
     * @param weatherInfo weatherInfo
     */
    @SuppressWarnings("deprecation")
    private void setUpdateTime(WeatherInfo weatherInfo) {
        if (weatherInfo.getUpdateTime() != null) {
            long now = System.currentTimeMillis();
            SharedPreferences share = getActivity().getSharedPreferences(
                    WeacConstants.BASE64, Activity.MODE_PRIVATE);
            // 最近一次天气更新时间
            long lastTime = share.getLong(getString(R.string.city_weather_update_time,
                    weatherInfo.getCity()), 0);
            // 更新间隔时间（小时）
            long minuteD = (now - lastTime) / 1000 / 60 / 60;
            // 更新时间
            String updateTime;
            if (minuteD < 24) {
                updateTime = String.format(getString(R.string.update_time), weatherInfo.getUpdateTime());
            } else if (minuteD >= 24 && minuteD < 48) {
                updateTime = String.format(getString(R.string.update_time2), weatherInfo.getUpdateTime());
            } else if (minuteD >= 48 && minuteD < 72) {
                updateTime = String.format(getString(R.string.update_time3), weatherInfo.getUpdateTime());
            } else if (minuteD >= 72 && minuteD < 96) {
                updateTime = String.format(getString(R.string.update_time4), 3);
            } else if (minuteD >= 96 && minuteD < 120) {
                updateTime = String.format(getString(R.string.update_time4), 4);
            } else if (minuteD >= 120 && minuteD < 144) {
                updateTime = String.format(getString(R.string.update_time4), 5);
            } else if (minuteD >= 144 && minuteD < 168) {
                updateTime = String.format(getString(R.string.update_time4), 6);
            } else {
                updateTime = getString(R.string.data_void);
            }
            mUpdateTimeTv.setText(updateTime);
            // 当不是数据过期
            if (!updateTime.equals(getString(R.string.data_void))) {
                mUpdateTimeTv.setTextColor(getResources().getColor(R.color.white_trans60));
            } else {
                mUpdateTimeTv.setTextColor(getResources().getColor(R.color.red));
            }
        } else {
            mUpdateTimeTv.setText(getString(R.string.dash));
            mUpdateTimeTv.setTextColor(getResources().getColor(R.color.white_trans60));
        }
    }

    /**
     * 设置预警信息
     *
     * @param weatherInfo weatherInfo
     */
    private void setAlarmInfo(final WeatherInfo weatherInfo) {
        if (weatherInfo.getAlarmType() != null) {
            mAlarmTv.setVisibility(View.VISIBLE);
            mAlarmTv.setText(getString(R.string.alarm, weatherInfo.getAlarmType()));
            mAlarmTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 警报详情
                    String detail = weatherInfo.getAlarmDetail();
                    // 替换换行"\r\n"  \\\：转义字符
                    detail = detail.replaceAll("\\\\r\\\\n", "");
                    String format;
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                .parse(weatherInfo.getAlarmTime());
                        format = new SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault()).format(date);
                    } catch (ParseException e) {
                        LogUtil.e(e);
                        format = weatherInfo.getAlarmTime();
                    }
                    String time = getString(R.string.release_time, format);

//                    Intent intent = new Intent(getActivity(), WeatherAlarmActivity.class);
//                    intent.putExtra(WeacConstants.TITLE, getString(R.string.alarm_title,
//                            weatherInfo.getAlarmType(), weatherInfo.getAlarmDegree()));
//                    intent.putExtra(WeacConstants.DETAIL, detail);
//                    intent.putExtra(WeacConstants.TIME, time);
//                    startActivity(intent);
                }
            });
        } else {
            mAlarmTv.setVisibility(View.GONE);
        }
    }

    /**
     * 设置城市名
     *
     * @param weatherInfo weatherInfo
     */
    private void setCityName(WeatherInfo weatherInfo) {
        mCityNameTv.setText(getString(R.string.dash));
        mCityNameTv.setCompoundDrawables(null, null, null, null);
    }

    /**
     * 取得风向图片id
     *
     * @param windDirection 风向
     * @return 风向图片id
     */
    private int getWindImageId(String windDirection) {
        int imgId;
        switch (windDirection) {
            case "南风":
                imgId = R.drawable.ic_wind_1;
                break;
            case "西南风":
                imgId = R.drawable.ic_wind_2;
                break;
            case "西风":
                imgId = R.drawable.ic_wind_3;
                break;
            case "西北风":
                imgId = R.drawable.ic_wind_4;
                break;
            case "北风":
                imgId = R.drawable.ic_wind_5;
                break;
            case "东北风":
                imgId = R.drawable.ic_wind_6;
                break;
            case "东风":
                imgId = R.drawable.ic_wind_7;
                break;
            case "东南风":
                imgId = R.drawable.ic_wind_8;
                break;
            default:
                imgId = R.drawable.ic_wind_3;
                break;
        }
        return imgId;
    }

    /**
     * 设置左侧图片
     *
     * @param tv      textView
     * @param imageId 图片id
     */
    private void setImage(TextView tv, int imageId) {
        @SuppressWarnings("deprecation") Drawable drawable = getResources().getDrawable(imageId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                    drawable.getMinimumHeight());
            // 设置图片
            tv.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 取得aqi图片id
     *
     * @param quality 大气质量
     * @return aqi图片id
     */
    private int getQualityImageId(String quality) {
        int imgId;
        switch (quality) {
            case "优":
                imgId = R.drawable.ic_quality_nice;
                break;
            case "良":
                imgId = R.drawable.ic_quality_good;
                break;
            case "轻度污染":
                imgId = R.drawable.ic_quality_little;
                break;
            case "中度污染":
                imgId = R.drawable.ic_quality_medium;
                break;
            case "重度污染":
                imgId = R.drawable.ic_quality_serious;
                break;
            case "严重污染":
                imgId = R.drawable.ic_quality_terrible;
                break;
            default:
                imgId = R.drawable.ic_quality_nice;
                break;
        }
        return imgId;
    }

    /**
     * 设置生活指数
     *
     * @param index 生活指数信息
     */

    private void setLifeIndex(WeatherLifeIndex index) {
        /*switch (index.getIndexName()) {
            case "雨伞指数":
                mLifeIndexUmbrellaTv.setText(index.getIndexValue());
                mLifeIndexUmbrellaDetail = index.getIndexDetail();
                break;
            case "紫外线强度":
                mLifeIndexUltravioletRaysTv.setText(index.getIndexValue());
                mLifeIndexUltravioletRaysDetail = index.getIndexDetail();
                break;
            case "穿衣指数":
                mLifeIndexDressTv.setText(index.getIndexValue());
                mLifeIndexDressDetail = index.getIndexDetail();
                break;
            case "感冒指数":
                mLifeIndexColdTv.setText(index.getIndexValue());
                mLifeIndexColdDetail = index.getIndexDetail();
                break;
            case "晨练指数":
                mLifeIndexMorningExerciseTv.setText(index.getIndexValue());
                mLifeIndexMorningExerciseDetail = index.getIndexDetail();
                break;
            case "运动指数":
                mLifeIndexSportTv.setText(index.getIndexValue());
                mLifeIndexSportDetail = index.getIndexDetail();
                break;
            case "洗车指数":
                mLifeIndexCarWashTv.setText(index.getIndexValue());
                mLifeIndexCarWashDetail = index.getIndexDetail();
                break;
            case "晾晒指数":
                mLifeIndexAirCureTv.setText(index.getIndexValue());
                mLifeIndexAirCureDetail = index.getIndexDetail();
                break;
        }*/
    }

    /**
     * 取得温度
     *
     * @param temp 温度信息
     * @return 温度
     */
    private int getTemp(String temp) {
        String temperature;
        if (!temp.contains("-")) {
            if (temp.length() == 6) {
                temperature = temp.substring(3, 5);
            } else {
                temperature = temp.substring(3, 4);
            }
        } else {
            if (temp.length() == 7) {
                temperature = temp.substring(3, 6);
            } else {
                temperature = temp.substring(3, 5);
            }
        }
        return Integer.parseInt(temperature);
    }

    /**
     * 截取日期和星期
     *
     * @param date 日期信息
     * @return 包含日期和星期的数组
     */
    private String[] getDay(String date) {
        String[] date1 = new String[2];
        if (date.length() == 5) {
            date1[0] = date.substring(0, 2);
            date1[1] = date.substring(2);
        } else {
            date1[0] = date.substring(0, 3);
            date1[1] = date.substring(3);
        }
        return date1;
    }

    /**
     * 转换周的标题
     *
     * @param week 需要转换的周标题
     * @return 周的标题
     */
    private String getWeek(String week) {
        String week1;
        switch (week) {
            case "星期一":
                week1 = getString(R.string.monday);
                break;
            case "星期二":
                week1 = getString(R.string.tuesday);
                break;
            case "星期三":
                week1 = getString(R.string.wednesday);
                break;
            case "星期四":
                week1 = getString(R.string.thursday);
                break;
            case "星期五":
                week1 = getString(R.string.friday);
                break;
            case "星期六":
                week1 = getString(R.string.saturday);
                break;
            case "星期天":
            case "星期日":
                week1 = getString(R.string.sunday);
                break;
            default:
                week1 = week;
                break;
        }
        return week1;
    }

    /**
     * 设置温度图片
     *
     * @param temp1     温度
     * @param imageView imageView控件
     */
    private void setTemperatureImage(int temp1, ImageView imageView) {
        switch (temp1) {
            case 0:
                imageView.setImageResource(R.drawable.number_0);
                break;
            case 1:
                imageView.setImageResource(R.drawable.number_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.number_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.number_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.number_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.number_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.number_6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.number_7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.number_8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.number_9);
                break;
            default:
                imageView.setImageResource(R.drawable.number_0);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAlpha = 0;
        if (mHandler != null) {
            mHandler.removeCallbacks(mRun);
        }
    }
}
