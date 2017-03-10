package com.mygdx.Lucky_Jumper.Resources;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by alexey on 09.03.17.
 */

 public class TexturesResources {

    private AssetManager manager;


    // Кнопки
    public  Texture button_play;
    public Texture button_play_pressed;

    public  Texture button_setting;
    public Texture button_setting_pressed;

    public Texture button_pause;
    public Texture button_pause_pressed;

    public Texture button_to_main_menu;
    public Texture button_to_main_menu_pressed;

    public Texture button_resume;
    public Texture button_resume_pressed;

    public Texture button_shop;
    public Texture button_shop_pressed;

    public Texture shop_button_boosters;
    public Texture shop_button_boosters_pressed;

    public Texture button_back;
    public Texture button_back_pressed;

    public Texture shop_button_clock_boosters;
    public Texture shop_button_clock_boosters_pressed;

    public Texture shop_button_platform_booster;
    public Texture shop_button_platform_booster_pressed;

    public Texture button_show_help;
    public Texture button_show_help_pressed;

    public Texture button_retry;
    public Texture button_retry_pressed;

    public Texture shop_button_double_neuron_points;
    public Texture shop_button_double_neuron_points_pressed;




    // Бэкграунды
    public Texture menu_state_bg;
    public Texture play_state_background1;
    public Texture play_state_background2;

    public Texture pause_state_background;

    public Texture quad;

    public Texture shop_item_background;

    public Texture scrool_box_border;
    public Texture training_background;

    //Модель персонажа
    public Texture player_walk_1;
    public Texture player_walk_2;

    //Платформы
    public Texture platfomr_1;
    public Texture start_platform;
    public Texture platfomr_1_boost;
    public Texture platform_bright;

    //другие спрайты
    public Texture neuron;
    public Texture neuron_points;
    public Texture clock;
    public Texture time_line;
    public Texture time_line_frame;
    public Texture shop_booster_level_active;
    public Texture shop_booster_level_non_active;
    public Texture platformBoost;
    public Texture double_neuron_points;


    public TexturesResources()
    {
        manager = new AssetManager();

    }

    public boolean isLoaded()
    {
        return manager.update();

    }

    public float getProgress()
    {
        return manager.getProgress();

    }


    public void LoadingTextures()
    {

        manager.load(URL.button_play,Texture.class);
        manager.load(URL.button_play_pressed,Texture.class);

        manager.load(URL.button_setting,Texture.class);
        manager.load(URL.button_setting_pressed,Texture.class);

        manager.load(URL.button_pause,Texture.class);
        manager.load(URL.button_pause_pressed,Texture.class);

        manager.load(URL.button_to_main_menu,Texture.class);
        manager.load(URL.button_to_main_menu_pressed,Texture.class);

        manager.load(URL.button_resume,Texture.class);
        manager.load(URL.button_resume_pressed,Texture.class);

        manager.load(URL.button_shop,Texture.class);
        manager.load(URL.button_shop_pressed,Texture.class);

        manager.load(URL.shop_button_boosters,Texture.class);
        manager.load(URL.shop_button_boosters_pressed,Texture.class);

        manager.load(URL.button_back,Texture.class);
        manager.load(URL.button_back_pressed,Texture.class);

        manager.load(URL.shop_button_clock_boosters,Texture.class);
        manager.load(URL.shop_button_clock_boosters_pressed,Texture.class);

        manager.load(URL.shop_button_platform_booster,Texture.class);
        manager.load(URL.shop_button_platform_booster_pressed,Texture.class);

        manager.load(URL.button_show_help,Texture.class);
        manager.load(URL.button_show_help_pressed,Texture.class);

        manager.load(URL.button_retry,Texture.class);
        manager.load(URL.button_retry_pressed,Texture.class);

        manager.load(URL.shop_button_double_neuron_points,Texture.class);
        manager.load(URL.shop_button_double_neuron_points_pressed,Texture.class);

        // Бэкграунды
        manager.load(URL.menu_state_bg,Texture.class);
        manager.load(URL.play_state_background1,Texture.class);
        manager.load(URL.play_state_background2,Texture.class);

        manager.load(URL.pause_state_background,Texture.class);

        manager.load(URL.quad,Texture.class);

        manager.load(URL.shop_item_background,Texture.class);

        manager.load(URL.scrool_box_border,Texture.class);
        manager.load(URL.training_background,Texture.class);

        //Модель персонажа
        manager.load(URL.player_walk_1,Texture.class);
        manager.load(URL.player_walk_2,Texture.class);

        //Платформы
        manager.load(URL.platfomr_1,Texture.class);
        manager.load(URL.start_platform,Texture.class);
        manager.load(URL.platfomr_1_boost,Texture.class);
        manager.load(URL.platform_bright,Texture.class);

        //другие спрайты
        manager.load(URL.neuron,Texture.class);
        manager.load(URL.neuron_points,Texture.class);
        manager.load(URL.clock,Texture.class);
        manager.load(URL.time_line,Texture.class);
        manager.load(URL.time_line_frame,Texture.class);
        manager.load(URL.shop_booster_level_active,Texture.class);
        manager.load(URL.shop_booster_level_non_active,Texture.class);
        manager.load(URL.platformBoost,Texture.class);
        manager.load(URL.double_neuron_points,Texture.class);
    }

    public void InitializeTextures()
    {
        button_play=manager.get(URL.button_play);
        button_play_pressed=manager.get(URL.button_play_pressed);

        button_setting=manager.get(URL.button_setting);
        button_setting_pressed=manager.get(URL.button_setting_pressed);

        button_pause= manager.get(URL.button_pause);
        button_pause_pressed= manager.get(URL.button_pause_pressed);

        button_to_main_menu= manager.get(URL.button_to_main_menu);
        button_to_main_menu_pressed= manager.get(URL.button_to_main_menu_pressed);

        button_resume= manager.get(URL.button_resume);
        button_resume_pressed= manager.get(URL.button_resume_pressed);

        button_shop= manager.get(URL.button_shop);
        button_shop_pressed= manager.get(URL.button_shop_pressed);

        shop_button_boosters= manager.get(URL.shop_button_boosters);
        shop_button_boosters_pressed= manager.get(URL.shop_button_boosters_pressed);

        button_back= manager.get(URL.button_back);
        button_back_pressed= manager.get(URL.button_back_pressed);

        shop_button_clock_boosters=manager.get(URL.shop_button_clock_boosters);
        shop_button_clock_boosters_pressed=manager.get(URL.shop_button_clock_boosters_pressed);

        shop_button_platform_booster=manager.get(URL.shop_button_platform_booster);
        shop_button_platform_booster_pressed= manager.get(URL.shop_button_platform_booster_pressed);

        button_show_help= manager.get(URL.button_show_help);
        button_show_help_pressed= manager.get(URL.button_show_help_pressed);

        button_retry= manager.get(URL.button_retry);
        button_retry_pressed= manager.get(URL.button_retry_pressed);

        shop_button_double_neuron_points= manager.get(URL.shop_button_double_neuron_points);
        shop_button_double_neuron_points_pressed= manager.get(URL.shop_button_double_neuron_points_pressed);

        // Бэкграунды
        menu_state_bg= manager.get(URL.menu_state_bg);
        play_state_background1= manager.get(URL.play_state_background1);
        play_state_background2= manager.get(URL.play_state_background2);

        pause_state_background= manager.get(URL.pause_state_background);

        quad= manager.get(URL.quad);

        shop_item_background= manager.get(URL.shop_item_background);

        scrool_box_border= manager.get(URL.scrool_box_border);
        training_background= manager.get(URL.training_background);

        //Модель персонажа
        player_walk_1= manager.get(URL.player_walk_1);
        player_walk_2= manager.get(URL.player_walk_2);

        //Платформы
        platfomr_1= manager.get(URL.platfomr_1);
        start_platform= manager.get(URL.start_platform);
        platfomr_1_boost= manager.get(URL.platfomr_1_boost);
        platform_bright= manager.get(URL.platform_bright);

        //другие спрайты
        neuron= manager.get(URL.neuron);
        neuron_points= manager.get(URL.neuron_points);
        clock= manager.get(URL.clock);
        time_line= manager.get(URL.time_line);
        time_line_frame= manager.get(URL.time_line_frame);
        shop_booster_level_active= manager.get(URL.shop_booster_level_active);
        shop_booster_level_non_active= manager.get(URL.shop_booster_level_non_active);
        platformBoost= manager.get(URL.platformBoost);
        double_neuron_points=manager.get(URL.double_neuron_points);





    }





}
