package com.sva.launcher;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * 界面文案翻译层。只做"查表替换"，查不到就原样返回，
 * 所以频道名、节目名、时间等动态内容不会被误伤。
 *
 * 语言开关存在 SharedPreferences（默认中文）。切到英文时 {@link #t} 直接透传，
 * 因为所有控件里存的一直是原始英文字面量。
 */
final class Lang {
    private Lang() { }

    private static final String PREFS = "sva_i18n";
    private static final String KEY_CHINESE = "chinese";

    /** 当前是否中文界面。 */
    private static volatile boolean chinese = true;

    private static final Map<String, String> MAP = new HashMap<>(512);

    /** 前缀规则：用于 "Label: 动态内容" 这类拼接串。长前缀放前面。 */
    private static final String[][] PREFIX = {
            {"Programme guide · daily refresh · ", "节目单 · 每日刷新 · "},
            {"On-device channel cache · ", "本机频道缓存 · "},
            {"Google Photos album · ", "Google 相册 · "},
            {"Google Photos ", "Google 相册 "},
            {"Calendar refresh failed · ", "日历刷新失败 · "},
            {"Calendar refresh failed: ", "日历刷新失败："},
            {"Background slideshow updated · ", "背景幻灯已更新 · "},
            {"Google Photos slideshow updated · ", "Google 相册幻灯已更新 · "},
            {"Lunar illumination  ·  ", "月面亮度  ·  "},
            {"Weather location · ", "天气位置 · "},
            {"Public calendar cache · ", "公共日历缓存 · "},
            {"Schedule unavailable on ", "节目表不可用："},
            {"Open TV guide  →", "打开电视节目单  →"},
            {"Current folder: ", "当前文件夹："},
            {"Album ready · ", "相册就绪 · "},
            {"Last refreshed ", "上次刷新："},
            {"Photo folder · ", "照片文件夹 · "},
            {"Photo source · ", "图片来源 · "},
            {"Restore failed: ", "恢复失败："},
            {"Backup failed: ", "备份失败："},
            {"Search failed · ", "搜索失败 · "},
            {"Refresh failed · ", "刷新失败 · "},
            {"Calendar refreshed · ", "日历已刷新 · "},
            {"Starting soon · ", "即将开始 · "},
            {"Edit artwork for ", "修改封面："},
            {"Edit title for ", "修改标题："},
            {"Feels like ", "体感 "},
            {"Artwork: ", "封面："},
            {"Category: ", "分类："},
            {"Added to favourites: ", "已加入收藏："},
            {"Removed from favourites: ", "已移出收藏："},
            {"Source exceeds the ", "来源大小超过 "},
            {"Could not read channel cache ", "无法读取频道缓存 "},
            {"Calendar feed returned HTTP ", "日历订阅返回 HTTP "},
            {"Watch ", "观看 "},
            {"Added to ", "已添加到 "},
            {"Removed from ", "已移除 "},
            {"Remove ", "移除 "},
            {"Replace ", "替换 "},
            {"Button ", "按键 "},
            {"Loading ", "加载中 "},
            {"Now playing: ", "正在播放："},
            {"Next: ", "接下来："},
            {"Next · ", "接下来 · "},
            {"Up next · ", "接下来 · "},
            {"Now · ", "现在 · "},
            {"ON NOW  ·  ", "正在播出  ·  "},
            {"Updated ", "更新于 "},
            {"USB · ", "U 盘 · "},
            {"Could not load playlist: ", "无法加载播放列表："},
            {"Showing saved channels. Channel refresh failed: ", "正在显示已保存的频道 · 频道刷新失败："},
            {"Showing saved channels. Guide refresh failed: ", "正在显示已保存的频道 · 节目单刷新失败："},
            {"Weather service returned ", "天气服务返回 "},
            {"About ", "关于 "},
    };

    /** 后缀规则：用于 "动态内容 + 英文尾巴"。 */
    private static final String[][] SUFFIX = {
            {", live now. Press OK to watch.", "，正在直播，按确定键观看。"},
            {". Press OK for reminders.", "，按确定键设置提醒。"},
            {" · no link configured yet", " · 尚未配置链接"},
            {" · All day", " · 全天"},
            {" · Hidden", " · 已隐藏"},
            {" from Saved", "（收藏）"},
            {"  ·  OK to watch channel", "  ·  按确定键观看"},
            {"  ·  Reminder set", "  ·  已设提醒"},
            {"  ·  OK for reminders", "  ·  按确定键设提醒"},
            {" minutes", " 分钟"},
            {" minute", " 分钟"},
            {" seconds", " 秒"},
            {" photos", " 张照片"},
            {" images", " 张图片"},
            {" MB size limit.", " MB 上限。"},
            {" playback failed. Check the channel license settings and TV support.",
                    " 播放失败，请检查该频道的授权设置与电视是否支持。"},
            {", favourite", "，已收藏"},
            {", selected", "，已选中"},
            {", folder", "，文件夹"},
            {", file", "，文件"},
    };

    static {
        // ---- 首页标签 ----
        put("Home", "首页");
        put("Live TV", "直播");
        put("Games", "游戏");
        put("Events", "日程");
        put("Saved", "收藏");
        put("Apps", "应用");

        // ---- 设置分区 ----
        put("Channels", "频道");
        put("Appearance", "外观");
        put("Clock", "时钟");
        put("Playback & DRM", "播放与 DRM");
        put("Home button", "主页键");
        put("Backup & restore", "备份与恢复");
        put("YouTube", "YouTube");
        put("Calendar", "日历");
        put("Schedules", "定时");
        put("Weather", "天气");
        put("Navigation", "导航");
        put("Cache & Guide status", "缓存与节目单状态");

        // ---- 语言开关 ----
        put("Language", "语言");
        put("Interface language. Switching takes effect immediately.", "界面语言，切换后立即生效。");

        // ---- 通用按钮 ----
        put("Save", "保存");
        put("Cancel", "取消");
        put("Back", "返回");
        put("OK", "确定");
        put("Accept", "接受");
        put("Delete", "删除");
        put("Done", "完成");
        put("Close", "关闭");
        put("Retry", "重试");
        put("Refresh", "刷新");
        put("Add", "添加");
        put("Edit", "编辑");
        put("Clear", "清除");
        put("Rename", "重命名");
        put("Replace", "替换");
        put("Restore", "恢复");
        put("Export", "导出");
        put("Import", "导入");

        // ---- 播放页 ----
        put("Playing · Up/Down to change channel", "播放中 · 上下键切换频道");
        put("Stream ended · Back to return", "播放结束 · 按返回键退出");
        put("Press OK to watch this channel", "按确定键观看此频道");
        put("Pause playback", "暂停播放");
        put("Back to Home", "返回首页");
        put("Open full-screen TV guide", "打开全屏电视节目单");
        put("Could not play this channel. The stream or codec may not be supported by the TV.",
                "无法播放此频道。该流或编码可能不被电视支持。");
        put("Missing channel stream URL.", "缺少频道流地址。");
        put("Playback session is unavailable. Go back and select the channel again.",
                "播放会话已失效，请返回重新选择频道。");
        put("Invalid channel playback configuration.", "频道播放配置无效。");
        put("Resume playback", "继续播放");
        put("Return to current programme", "回到当前节目");
        put("Play", "播放");
        put("Preview", "预览");
        put("Select to watch", "选择以观看");
        put("Watch channel", "观看频道");
        put("Find a channel", "查找频道");
        put("Channel", "频道");
        put("All channels", "全部频道");
        put("All channels  ▾", "全部频道  ▾");
        put("Choose channel category", "选择频道分类");
        put("Visible categories", "显示的分类");
        put("Uncategorized", "未分类");

        // ---- 节目单 ----
        put("TODAY", "今天");
        put("TV GUIDE", "电视节目单");
        put("UP NEXT", "接下来");
        put("UPCOMING", "即将播出");
        put("NOW PLAYING", "正在播放");
        put("Now Playing", "正在播放");
        put("LIVE TV", "直播");
        put("● LIVE", "● 直播");
        put("● LIVE NOW", "● 正在直播");
        put("◷ REMINDER", "◷ 提醒");
        put("SCHEDULE UNAVAILABLE", "节目表不可用");
        put("YOUR TV SCHEDULE", "你的电视日程");
        put("TV guide", "电视节目单");
        put("Programme guide", "电视节目单");
        put("Programme guide · XML, XML.GZ or JSON", "节目单 · XML、XML.GZ 或 JSON");
        put("No schedule", "无节目表");
        put("No events today", "今天没有节目");
        put("No programme information", "无节目信息");
        put("Channel category", "频道分类");
        put("This programme has already started.", "该节目已经开始。");
        put("Remind me", "提醒我");
        put("Remove reminder", "取消提醒");
        put("Dismiss", "忽略");
        put("Reminders appear five minutes before the show while Sva is open.",
                "节目开始前五分钟，Sva 打开时会提醒你。");
        put("Sva's imported copy, artwork, and battery save will be deleted. Your original file will not be changed.",
                "Sva 导入的副本、封面和存档将被删除，你的原始文件不会被改动。");
        put("That reminder channel is no longer available.", "该提醒对应的频道已不可用。");
        put("Explore the schedule, see what's on now, and set reminders.", "查看节目表与当前节目，并设置提醒。");
        put("No visible channels. Add a playlist or restore hidden channels in Settings.",
                "没有可见频道。请在设置中添加播放列表或恢复隐藏的频道。");
        put("Configure Live TV sources in Settings", "请先在设置中配置直播源");
        put("All channels are hidden. Restore channels in Settings.", "所有频道已隐藏。请在设置中恢复。");
        put("Add a playlist and programme guide in Settings.", "请在设置中添加播放列表和节目单。");
        put("Add a playlist in Settings to browse live TV.", "请在设置中添加播放列表以浏览直播。");
        put("Add an M3U and optional XMLTV URL in Settings to get started.",
                "请先在设置中填入 M3U 及可选的 XMLTV 地址。");
        put("Choose which channels appear in Live TV", "选择直播中显示哪些频道");
        put("Choose which categories appear and their order", "选择显示哪些分类及其顺序");
        put("Refreshing channels…", "正在刷新频道…");
        put("Refreshing public calendar…", "正在刷新公共日历…");
        put("Refreshing programme guide…", "正在刷新节目单…");
        put("Updating today's programme guide…", "正在更新今日节目单…");
        put("Refresh channels", "刷新频道");
        put("Refresh programme guide now", "立即刷新节目单");
        put("Refresh saved calendar now", "立即刷新已保存日历");
        put("Refresh in Settings", "在设置中刷新");
        put("Refresh now", "立即刷新");
        put("Refresh intervals", "刷新间隔");
        put("Loading channels…", "正在加载频道…");
        put("Loading your channels…", "正在加载频道…");
        put("Search by channel name", "按频道名搜索");
        put("Search", "搜索");
        put("Select", "选择");
        put("Separate", "单独");

        // ---- 收藏 / 提示 ----
        put("Add to Saved", "加入收藏");
        put("Added to Saved.", "已加入收藏。");
        put("Removed from Saved.", "已从收藏移除。");
        put("Saved.", "已收藏。");
        put("That link is already in Saved.", "该链接已在收藏中。");
        put("Saved shortcuts", "已保存的快捷方式");
        put("Save videos and playlists in Saved", "在收藏中保存视频与播放列表");
        put("No saved YouTube shortcuts yet. Add a video or playlist in Settings.",
                "尚未收藏 YouTube 快捷方式，请在设置中添加视频或播放列表。");
        put("No YouTube cards saved yet.", "尚未收藏任何 YouTube 卡片。");
        put("Calendar removed.", "日历已移除。");
        put("Backup restored.", "备份已恢复。");
        put("Backup saved.", "备份已保存。");
        put("Creating launcher backup…", "正在创建桌面备份…");
        put("Restoring launcher backup…", "正在恢复桌面备份…");
        put("Discard unsaved edits?", "放弃未保存的修改？");
        put("Loading public album…", "正在加载公开相册…");
        put("Weather unavailable", "天气不可用");
        put("Weather not set", "未设置天气");
        put("Weather refreshed.", "天气已刷新。");
        put("Refreshing weather…", "正在刷新天气…");
        put("Could not refresh weather.", "无法刷新天气。");
        put("Choose a location.", "请选择位置。");
        put("Choose a location first.", "请先选择位置。");
        put("Changes appear on the Home clock immediately.", "更改会立即显示在首页时钟上。");
        put("Choose and order your home shortcuts", "选择和排序首页快捷方式");

        // ---- 骨架屏 / 其它 ----
        put("Sva Launcher", "Sva 桌面");
        put("Sva Memories", "Sva 回忆");
        put("Appearance settings", "外观设置");
        put("Launcher Settings", "桌面设置");
        put("Settings", "设置");
        put("Settings shortcuts", "设置快捷方式");
        put("Configure in Settings", "在设置中配置");
        put("Android Settings", "安卓设置");
        put("Content", "内容");
        put("Actions", "操作");
        put("Activation", "激活");
        put("Atmosphere", "氛围");
        put("Overlays", "叠加层");
        put("Theme", "主题");
        put("Home & Display", "首页与显示");
        put("Defaults", "默认值");
        put("Network", "网络");
        put("Library", "游戏库");
        put("Controllers", "手柄");
        put("Title", "标题");
        put("Art", "封面");
        put("Artwork", "封面");
        put("Photo", "照片");
        put("Slideshow", "幻灯播放");
        put("Screensaver", "屏幕保护");
        put("Home album", "首页相册");
        put("Location", "位置");
        put("Units", "单位");
        put("Temperature", "温度");
        put("Wind speed", "风速");
        put("Celsius", "摄氏度");
        put("Fahrenheit", "华氏度");
        put("Today", "今天");
        put("Now", "现在");
        put("Last", "最后");
        put("Next month", "下个月");
        put("Previous month", "上个月");

        // ---- 首页 / 设置高频短语 ----
        put("Add a calendar in Settings", "请在设置中添加日历");
        put("Add shortcut", "添加快捷方式");
        put("Add weather", "添加天气");
        put("Add art", "添加封面");
        put("Add files", "添加文件");
        put("Add folder", "添加文件夹");
        put("Add games from folder", "从文件夹添加游戏");
        put("Add NES games", "添加 NES 游戏");
        put("Import games", "导入游戏");
        put("Add a public calendar URL first.", "请先添加公共日历网址。");
        put("Calendar feed", "日历订阅");
        put("Calendar event", "日历事件");
        put("All day", "全天");
        put("Backup filename", "备份文件名");
        put("Save the playlist URL before refreshing channels.", "请先保存播放列表网址再刷新频道。");
        put("Save the programme guide URL before refreshing it.", "请先保存节目单网址再刷新。");
        put("Save the calendar URL before refreshing.", "请先保存日历网址再刷新。");
        put("Nothing to show yet", "暂无内容");
        put("Restoring replaces the current launcher settings and channel preferences. Keep backup files private.",
                "恢复会覆盖当前的桌面设置与频道偏好。请妥善保管备份文件。");
        put("Settings saved.", "设置已保存。");
        put("Never", "从不");
        put("Always", "总是");
        put("On", "开");
        put("Off", "关");
        put("Enabled", "已启用");
        put("Disabled", "已禁用");
        put("Hidden", "已隐藏");
        put("Visible", "可见");
        put("Light", "浅色");
        put("Dark", "深色");
        put("System", "系统");
        put("System default", "跟随系统");
        put("Default", "默认");
        put("Custom", "自定义");
        put("Small", "小");
        put("Large", "大");
        put("24-hour time", "24 小时制");
        put("Show date", "显示日期");
        put("Clock display", "时钟显示");
        put("Time and date on Home", "首页的时间与日期");

        // ---- 存储 / 屏保 ----
        put("Storage locations", "存储位置");
        put("Storage access needed", "需要存储权限");
        put("Allow storage access", "允许访问存储");
        put("Allow storage access to browse files on this device and connected USB drives.",
                "需要存储权限才能浏览本机及已连接 U 盘中的文件。");
        put("Choose internal storage or a connected USB drive.", "选择内部存储或已连接的 U 盘。");
        put("Internal storage", "内部存储");
        put("No local storage found", "未找到本地存储");
        put("Storage settings are unavailable.", "存储设置不可用。");
        put("Choose photo folder", "选择照片文件夹");
        put("Choose single wallpaper", "选择单张壁纸");
        put("Clear photo folder", "清除照片文件夹");
        put("Clear single wallpaper", "清除单张壁纸");
        put("Clear Google Photos album", "清除 Google 相册");
        put("Choose when content updates automatically", "设置内容自动更新时机");
        put("Screensaver now follows the Home album.", "屏保现在跟随首页相册。");
        put("Checking shared album…", "正在检查共享相册…");
        put("No images were found in that folder.", "该文件夹里没有找到图片。");
        put("Could not read that folder. Keeping the saved slideshow.", "无法读取该文件夹，保留已保存的幻灯。");
        put("Add a personal caption", "添加个人说明");
        put("Photo backdrop and wallpaper", "照片背景与壁纸");
        put("Photo slideshow", "照片幻灯");
        put("Photo source", "图片来源");
        put("Photos unavailable · showing landscape wallpaper", "照片不可用 · 显示风景壁纸");
        put("No photo folder selected", "未选择照片文件夹");
        put("No single wallpaper — using the default landscape", "未设置单张壁纸 —— 使用默认风景图");
        put("Single wallpaper", "单张壁纸");
        put("Single wallpaper is set", "已设置单张壁纸");
        put("Change photo", "更换照片");
        put("Choose image", "选择图片");
        put("Check album", "检查相册");
        put("No Google Photos album selected", "未选择 Google 相册");
        put("Google Photos album", "Google 相册");
        put("Google Photos public album link", "Google 相册公开链接");
        put("Use Google Photos album", "使用 Google 相册");
        put("Use Home album", "使用首页相册");
        put("Separate album", "单独相册");
        put("Separate Google Photos album (optional)", "单独的 Google 相册（可选）");
        put("Enter a Google Photos shared album link, or leave empty to use Home.",
                "输入 Google 相册共享链接，留空则使用首页相册。");
        put("Leave empty to follow the Home album. Use an album shared with anyone who has the link.",
                "留空则跟随首页相册。请使用「知道链接的人均可查看」的相册。");
        put("Paste a Google Photos shared album link first.", "请先粘贴 Google 相册共享链接。");
        put("Paste a link shared with anyone", "粘贴「知道链接的人均可查看」的链接");
        put("No photos were found. Check that link sharing is on.", "未找到照片，请确认已开启链接共享。");
        put("No public photos were found in that album.", "该相册中没有公开照片。");
        put("Could not read album. Check the link, sharing settings and connection.",
                "无法读取相册，请检查链接、共享设置与网络连接。");
        put("Could not load that Google Photos album.", "无法加载该 Google 相册。");
        put("Add a Google Photos album in Screensaver settings", "请在屏保设置中添加 Google 相册");
        put("Add an album on Home or paste a separate album above.", "在首页添加相册，或在上方粘贴单独的相册。");
        put("Loading your photographs…", "正在加载照片…");
        put("Sva Memories · Personalize your idle screen", "Sva 回忆 · 个性化你的待机画面");
        put("Sva Memories · screensaver settings", "Sva 回忆 · 屏保设置");
        put("Start while Sva is idle", "Sva 空闲时启动");
        put("Runs while Sva is open. Playback and settings stay uninterrupted.",
                "仅在 Sva 打开时运行，播放与设置不会中断。");
        put("Save screensaver changes?", "保存屏保修改？");
        put("Your album and caption have unsaved changes.", "相册与说明有未保存的修改。");
        put("Caption (optional)", "说明文字（可选）");

        // ---- 文件选择 ----
        put("Select a file", "选择文件");
        put("Select a file with OK", "按确定键选择文件");
        put("Save here", "保存到此处");
        put("Use this folder", "使用此文件夹");
        put("Open a folder, then choose Use this folder", "打开文件夹后选择「使用此文件夹」");
        put("Enter a filename and save in this folder", "输入文件名并保存到此文件夹");
        put("Enter a filename without slashes", "输入不含斜杠的文件名");
        put("Enter at least two characters.", "请至少输入两个字符。");
        put("No matching files here", "此处没有匹配的文件");
        put("This folder cannot be read", "无法读取此文件夹");
        put("Could not create file", "无法创建文件");
        put("Could not create a file here.", "无法在此处创建文件。");
        put("Could not read that file.", "无法读取该文件。");
        put("Could not write to that file.", "无法写入该文件。");

        // ---- 游戏 ----
        put("Game title", "游戏名称");
        put("Could not save title.", "无法保存名称。");
        put("Could not save artwork.", "无法保存封面。");
        put("Could not remove game.", "无法移除游戏。");
        put("This game could not be started.", "该游戏无法启动。");
        put("Importing games…", "正在导入游戏…");
        put("Controller mapping saved.", "手柄映射已保存。");
        put("Could not save mapping.", "无法保存映射。");
        put("NINTENDO ENTERTAINMENT SYSTEM", "任天堂红白机");
        put("Import and manage your local NES library", "导入并管理本地 NES 游戏库");
        put("Your local NES library · user-provided .nes files only", "本地 NES 库 · 仅支持自备的 .nes 文件");
        put("No games yet. Add your own .nes files to get started.", "还没有游戏。添加你自己的 .nes 文件即可开始。");
        put("No games yet. Import your own .nes files to get started.", "还没有游戏。导入你自己的 .nes 文件即可开始。");
        put("Open game library and import", "打开游戏库并导入");
        put("Game controllers", "游戏手柄");
        put("Configure game controllers", "配置游戏手柄");
        put("Configure buttons for your connected gamepads", "为已连接的手柄配置按键");
        put("Connect a gamepad by Bluetooth or USB, then choose Refresh.",
                "通过蓝牙或 USB 连接手柄，然后选择「刷新」。");
        put("Connected controllers", "已连接的手柄");
        put("No controller connected", "未连接手柄");
        put("Refresh controllers", "刷新手柄列表");
        put("Map controller", "映射手柄");
        put("Button mapping", "按键映射");
        put("Press a button on the selected controller. Press Back to cancel without saving.",
                "按下所选手柄上的按键，按返回键取消且不保存。");

        // ---- 外观 / 正在播放 ----
        put("Follow your TV’s theme, or choose a light or dark appearance.",
                "跟随电视主题，或选择浅色／深色外观。");
        put("Appears on Home only while media is playing. Allow notification access to show playback from other apps.",
                "仅在播放媒体时显示于首页。允许通知访问可显示其他应用的播放。");
        put("Manage Now Playing access", "管理「正在播放」权限");
        put("Open Accessibility settings", "打开无障碍设置");
        put("Could not open Accessibility settings.", "无法打开无障碍设置。");
        put("Could not open Home settings.", "无法打开主屏幕设置。");
        put("Open Android Settings and allow Sva Now Playing notification access.",
                "请打开 Android 设置，允许 Sva 读取通知（正在播放）。");
        put("Playing on your TV", "正在电视上播放");
        put("Choose up to five apps for Home. Use arrows to change their order.",
                "最多可为首页选择五个应用，用方向键调整顺序。");
        put("No apps selected", "未选择应用");
        put("Loading apps…", "正在加载应用…");
        put("Override status", "接管状态");
        put("✓ Sva launcher override is enabled on this TV.", "✓ 本电视已启用 Sva 桌面接管。");
        put("✕ Sva launcher override is currently disabled.", "✕ 本电视当前未启用 Sva 桌面接管。");
        put("Open Sva when the TV Home button opens its built-in launcher",
                "电视主页键打开内置桌面时启动 Sva");
        put("Try setting Sva as the Home app", "试试把 Sva 设为桌面应用");
        put("Choose in Android TV settings", "在 Android TV 设置中选择");
        put("First after Home", "主页键后第一项");
        put("Second after Home", "主页键后第二项");
        put("Third after Home", "主页键后第三项");
        put("Show Live TV in navigation", "在导航中显示直播");
        put("Show Games in navigation", "在导航中显示游戏");
        put("Show Events in navigation", "在导航中显示日程");
        put("Item order", "项目顺序");
        put("Reset item order", "重置项目顺序");
        put("Restore schedule defaults", "恢复默认定时");

        // ---- 备份 ----
        put("Create backup", "创建备份");
        put("Create backup file", "创建备份文件");
        put("Save backup", "保存备份");
        put("Restore backup", "恢复备份");
        put("Restore from backup", "从备份恢复");
        put("Save or restore launcher settings and channels", "保存或恢复桌面设置与频道");
        put("Create a JSON backup of launcher settings, app and channel choices, and IPTV details.",
                "创建 JSON 备份，包含桌面设置、应用与频道选择、IPTV 信息。");
        put("Backup file is larger than 64 MB.", "备份文件超过 64 MB。");
        put("This backup file is not supported.", "不支持该备份文件。");
        put("The backup file is missing launcher or IPTV data.", "备份文件缺少桌面或 IPTV 数据。");
        put("The backup contains an incompatible preference type.", "备份中包含不兼容的配置项类型。");
        put("The backup contains an unknown preference type.", "备份中包含未知的配置项类型。");
        put("The backup contains an invalid preference.", "备份中包含无效配置项。");
        put("The backup contains an invalid number.", "备份中包含无效数字。");
        put("Unsupported set in preferences.", "配置中包含不支持的数据集。");
        put("Could not create the backup.", "无法创建备份。");
        put("Could not restore that backup.", "无法恢复该备份。");
        put("Could not save restored settings.", "无法保存已恢复的设置。");
        put("Discard", "放弃");
        put("Discard edits", "放弃修改");
        put("Keep editing", "继续编辑");
        put("Save changes", "保存修改");
        put("Playback settings saved", "播放设置已保存");
        put("No changes to save.", "没有需要保存的修改。");
        put("Settings saved. Automatic refresh uses the cadences under Schedules.",
                "设置已保存，自动刷新按「定时」中的间隔执行。");

        // ---- 播放列表 / DRM ----
        put("IPTV playlist", "IPTV 播放列表");
        put("M3U playlist", "M3U 播放列表");
        put("PLAYLIST", "播放列表");
        put("Playlist and programme guide", "播放列表与电视节目单");
        put("Playlist & Guide URLs", "播放列表与节目单地址");
        put("Playlist, DRM, or calendar changes have not been saved.", "播放列表、DRM 或日历的修改尚未保存。");
        put("Channel-specific KODIPROP settings in the playlist take priority over these defaults.",
                "播放列表中针对单个频道的 KODIPROP 设置优先于这些默认值。");
        put("Widevine DRM", "Widevine DRM");
        put("Widevine license URL", "Widevine 授权地址");
        put("Default Widevine license settings", "默认 Widevine 授权设置");
        put("Optional default license endpoint", "可选的默认授权地址");
        put("License request headers", "授权请求头");
        put("Could not start Widevine playback. Check the channel DRM settings.",
                "无法启动 Widevine 播放，请检查该频道的 DRM 设置。");
        put("Could not start ClearKey playback. Check the DASH format, keys or license URL.",
                "无法启动 ClearKey 播放，请检查 DASH 格式、密钥或授权地址。");
        put("Could not start this stream. Check the stream URL and playlist options.",
                "无法启动该流，请检查流地址与播放列表选项。");
        put("Could not open playback. Please try again.", "无法打开播放，请重试。");

        // ---- YouTube ----
        put("YouTube video link", "YouTube 视频链接");
        put("Use the YouTube title", "使用 YouTube 标题");
        put("Artwork video link · optional", "封面视频链接 · 可选");
        put("Card title · optional", "卡片标题 · 可选");
        put("Card title", "卡片标题");
        put("Card artwork", "卡片封面");
        put("Video or playlist link", "视频或播放列表链接");
        put("Enter a YouTube video link", "输入 YouTube 视频链接");
        put("Enter a valid YouTube video or playlist link.", "请输入有效的 YouTube 视频或播放列表链接。");
        put("Artwork must be a YouTube video link.", "封面必须是 YouTube 视频链接。");
        put("Paste a YouTube video link to use its thumbnail. Leave empty to retry automatic artwork.",
                "粘贴 YouTube 视频链接以使用其缩略图。留空则重试自动获取封面。");
        put("Clear the title to use the title provided by YouTube when available.",
                "清空标题即可采用 YouTube 提供的标题（如有）。");
        put("Use for playlist artwork", "用作播放列表封面");
        put("No app can open this YouTube link.", "没有应用可以打开该 YouTube 链接。");
        put("Could not open this YouTube link.", "无法打开该 YouTube 链接。");

        // ---- 天气 / 日历 ----
        put("Current conditions and a three-day forecast", "当前天气与三天预报");
        put("Local conditions", "本地天气");
        put("Forecast unavailable", "预报不可用");
        put("Current location", "当前位置");
        put("Use device location", "使用设备定位");
        put("Choose a city or use this TV's location to show weather on Home.",
                "选择城市或使用本电视的位置，在首页显示天气。");
        put("Search city or postal code", "搜索城市或邮编");
        put("Searching locations…", "正在搜索地点…");
        put("Location search failed.", "位置搜索失败。");
        put("Location denied. Search for a city instead.", "定位被拒绝，请改为搜索城市。");
        put("Location timed out. Try city search instead.", "定位超时，请改为搜索城市。");
        put("Location is unavailable on this TV.", "本电视无法定位。");
        put("Location permission was not granted.", "未授予定位权限。");
        put("No matching places found.", "未找到匹配的地点。");
        put("Turn on Android location services first.", "请先开启 Android 定位服务。");
        put("Finding this TV’s location…", "正在定位本电视…");
        put("Could not determine this TV’s location.", "无法确定本电视的位置。");
        put("Set up weather in Launcher Settings", "请在桌面设置中配置天气");
        put("Remove weather", "移除天气");
        put("Weather and events use your Home location and calendar.", "天气与日程使用首页的位置和日历。");
        put("Waning gibbous", "亏凸月");
        put("Waning gib…", "亏凸…");
        put("Public calendar", "公共日历");
        put("Public calendar iCal URL", "公共日历 iCal 地址");
        put("Show events from a public Google Calendar", "显示公共 Google 日历中的日程");
        put("Use a public Google Calendar iCal URL from Integrate calendar.",
                "使用公共 Google 日历「集成日历」中的 iCal 地址。");
        put("Save calendar and refresh", "保存日历并刷新");
        put("Could not refresh this calendar.", "无法刷新该日历。");
        put("Not refreshed yet", "尚未刷新");
        put("Your calendar", "你的日历");
        put("Your calendar is clear", "日历上没有日程");
        put("Open your calendar", "打开你的日历");
        put("No events planned", "暂无日程");
        put("No upcoming events", "暂无即将到来的日程");
        put("Your guide is ready when you are", "节目单已就绪");
        put("Calendar feed is larger than 6 MB.", "日历订阅超过 6 MB。");
        // ---- 屏保：氛围名与描述（数组，经 SettingsUi.choice / Lang.all 翻译）----
        put("Cinema", "影院");
        put("Darkroom", "暗房");
        put("Editorial", "杂志");
        put("Floating Prints", "飘浮相片");
        put("Monolith", "巨碑");
        put("Prism", "棱镜");
        put("Silhouette", "剪影");
        put("Travel Journal", "旅行手账");
        put("Photo Weave", "照片编织");
        put("Slow camera movement, soft black edge shading and a quiet cinematic fade.",
                "缓慢的镜头移动、柔和的黑色边缘与安静的电影式淡入淡出。");
        put("An amber-lit contact sheet, warm photographs and a passing trace of light.",
                "琥珀色调的相纸样张、温暖的照片与掠过的一道光。");
        put("An oversized photograph, ivory margins and beautifully set personal captions.",
                "放大的照片、象牙白留白，以及精心排版的个人说明。");
        put("Album photographs drift as layered paper prints against colors drawn from your images.",
                "相册照片如层叠的纸质相片漂浮，背景色取自你的图像。");
        put("A sculptural floating photograph, deep image-inspired color and quiet movement.",
                "雕塑般的悬浮照片、源自图像的深沉色彩与安静的律动。");
        put("Translucent planes, emerald light ribbons and a luminous photographic centrepiece.",
                "半透明平面、翠绿的光带与发光的照片主体。");
        put("Monochrome photography revealed through broad, moving vertical panels.",
                "黑白影像透过移动的宽幅竖条逐层显现。");
        put("Warm textured paper, photographic keepsakes and delicate route lines.",
                "温暖的纹理纸张、照片纪念物与纤细的路线。");
        put("Different album photographs woven into a slowly changing asymmetric grid.",
                "不同照片编织成缓慢变化的非对称网格。");
        put("Album unavailable · showing landscape wallpaper", "相册不可用 · 显示风景壁纸");
        put("Choose Sva Memories in Android TV settings for a system-wide screensaver. Previously downloaded photos work offline.",
                "在 Android TV 设置中选择 Sva 回忆，可设为全系统屏保。已下载的照片可离线播放。");
        put("This TV restricts system screensaver settings. Use Start while Sva is idle above, or open the TV's Screensaver / Ambient mode settings.",
                "本电视限制了系统屏保设置。请使用上方的「Sva 空闲时启动」，或在本电视的屏保／氛围模式设置中开启。");

        // ---- 文件选择器 ----
        put("Current folder", "当前文件夹");

        // ---- 设置页说明 / 游戏 / 刷新反馈（2026-10-19 第三轮补齐）----
        put("A photo folder or Google Photos album plays as a slideshow. It takes priority over a single wallpaper.", "照片文件夹或 Google 相册会作为幻灯播放，优先级高于单张壁纸。");
        put("Add a public Google Calendar iCal address (Google Calendar → Settings → Integrate calendar → Public address in iCal format). Events appear in the Events tab.", "添加公共 Google 日历的 iCal 地址（Google 日历 → 设置 → 集成日历 → iCal 格式的公开地址）。日程会显示在「日程」标签中。");
        put("Choose which categories appear in the navigation bar. Your games and calendar data stay saved.", "选择导航栏中显示哪些分类。游戏与日历数据会保留。");
        put("Home stays first. Select a category to change its position. Hidden categories keep their place.", "「首页」固定在最前。选择分类可调整位置，隐藏的分类会保留原位置。");
        put("Nestopia UE is embedded in Sva. On a TV remote, use the arrow buttons to move, OK for A, and hold OK for Start. Gamepads are assigned to players one and two automatically. During play, Back opens Resume, Restart, and Exit.", "Sva 内置 Nestopia UE。遥控器上用方向键移动，确定键为 A，长按确定键为 Start。手柄会自动分配为一号、二号玩家。游戏中按返回键可打开「继续／重开／退出」。");
        put("Paste a public YouTube video or playlist link to add a card to Saved. Add an optional title; the full artwork thumbnail stays visible on the card.", "粘贴公开的 YouTube 视频或播放列表链接，即可把卡片加入收藏。可选填标题，卡片上会完整显示封面图。");
        put("Paused keeps the current photo visible. Every launch refreshes Google Photos or weather once when Sva starts.", "「暂停」会保持当前照片不变。每次启动 Sva 时会刷新一次 Google 相册或天气。");
        put("Playlist and programme-guide refresh cadences are configured independently under Schedules. You can also refresh them manually here.", "播放列表与节目单的刷新周期在「定时」中分别设置，也可以在这里手动刷新。");
        put("Schedules run while Sva is open. Overdue content is checked when Home resumes; manual refresh buttons continue to work when automatic refresh is disabled.", "定时任务仅在 Sva 打开时运行。回到首页时会检查过期内容；关闭自动刷新时，手动刷新按钮依然可用。");
        put("Some TVs keep their built-in launcher as Home even when another launcher is selected. Enable Sva Launcher in Android Accessibility settings to reopen Sva whenever the TV opens its built-in launcher.", "部分电视即使选了其他桌面，仍会把内置桌面当作 Home。请在 Android 无障碍设置中启用 Sva 桌面，这样电视打开内置桌面时会回到 Sva。");
        put("Sva supports user-provided .nes files only. Games are copied into private app storage; original files are never changed.", "Sva 仅支持用户自备的 .nes 文件。游戏会复制到应用私有存储，原始文件不会被改动。");
        put("The on-device schedule is checked when Sva opens. Change its automatic refresh cadence under Schedules. Events appear while active.", "Sva 打开时会检查本机日程。可在「定时」中修改自动刷新周期，日程生效期间会显示。");
        put("Weather by Open-Meteo · Locations by GeoNames · Device location requested only on setup or manual refresh.", "天气数据来自 Open-Meteo · 地点数据来自 GeoNames · 仅在设置或手动刷新时请求设备定位。");
        put("XMLTV matches by tvg-id. JSON guides match channel IDs or a unique channel name. EPGSchedule times use India Standard Time.", "XMLTV 按 tvg-id 匹配；JSON 节目单按频道 ID 或唯一频道名匹配。EPGSchedule 使用印度标准时间。");
        put("Shown", "已显示");
        put("Move up", "上移");
        put("Move down", "下移");
        put("Remove", "移除");
        put("Watch", "观看");
        put("Back to Live TV", "返回直播");
        put("Favourites", "收藏");
        put("Exit to Sva", "返回 Sva");
        put("Restart", "重新开始");
        put("Resume", "继续");
        put("Pause", "暂停");
        put("Start", "开始");
        put("Left", "左");
        put("Right", "右");
        put("Up", "上");
        put("Down", "下");
        put("Choose an image file.", "请选择图片文件。");
        put("Could not finish import", "导入未能完成");
        put("Could not read artwork", "无法读取封面");
        put("Could not read file", "无法读取文件");
        put("File exceeds the import size limit.", "文件超过导入大小上限。");
        put("Missing folder", "缺少文件夹");
        put("NES file is larger than 32 MB.", "NES 文件超过 32 MB。");
        put("NES game", "NES 游戏");
        put("Not an iNES file", "不是 iNES 文件");
        put("Left-stick and directional-pad movement work automatically. Select a controller to remap its buttons.", "左摇杆与方向键可自动使用。选择手柄可重新映射按键。");
        put("Every launch", "每次启动");
        put("Manual only", "仅手动");
        put("Paused", "暂停");
        put("Full moon", "满月");
        put("New moon", "新月");
        put("YouTube video", "YouTube 视频");
        put("YouTube playlist", "YouTube 播放列表");
        put("No photos found. Check that the album is publicly shared.", "未找到照片，请确认相册已公开共享。");
        put("Enter a Google Photos shared album link.", "请输入 Google 相册共享链接。");
        put("The Google Photos album page is too large to read.", "Google 相册页面过大，无法读取。");
        put("The link did not open a Google Photos album.", "该链接没有打开 Google 相册。");
        put("This link did not return a public iCalendar feed.", "该链接未返回公开的 iCalendar 订阅。");
        put("Cannot save playback session.", "无法保存播放会话。");
        put("Invalid playback session.", "播放会话无效。");
        put("Add an M3U playlist URL in Settings.", "请在设置中添加 M3U 播放列表地址。");
        put("Add and save a programme guide URL in Settings.", "请在设置中添加并保存节目单地址。");
        put("Channels updated.", "频道已更新。");
        put("Programme guide updated.", "节目单已更新。");
        put("No channels found in the playlist.", "播放列表中没有找到频道。");
        put("Playlist was empty. Keeping saved channels.", "播放列表为空，保留已保存的频道。");
        put("Playlist is larger than 24 MB.", "播放列表超过 24 MB。");
        put("JSON guide is larger than 16 MB.", "JSON 节目单超过 16 MB。");
        put("JSON guide contains no channels.", "JSON 节目单中没有频道。");
        put("JSON guide contains no channel entries.", "JSON 节目单中没有频道条目。");
        put("Guide has no current or upcoming programmes.", "节目单中没有当前或将来的节目。");
        put("JSON guide has no current or upcoming programmes.", "JSON 节目单中没有当前或将来的节目。");
        put("Refresh channels first to load the programme guide.", "请先刷新频道，再加载节目单。");
        put("Playlist URL changed during refresh. Refresh channels again.", "刷新过程中播放列表地址已变更，请再刷新一次频道。");
        put("Programme guide URL changed during refresh. Refresh the guide again.", "刷新过程中节目单地址已变更，请再刷新一次节目单。");
        put("Backup contains an invalid channel entry.", "备份中包含无效的频道条目。");
        put("Backup contains too many channels.", "备份中频道数量过多。");
        put("Backup contains a channel without a name or stream URL.", "备份中有频道缺少名称或流地址。");
        put("Guide contains no valid programmes matching playlist tvg-id values.", "节目单中没有与播放列表 tvg-id 匹配的节目。");
        put("JSON guide contains no valid programmes matching playlist channel IDs or names.", "JSON 节目单中没有与频道 ID 或名称匹配的节目。");
        put("Playlist ended a channel entry before its stream URL.", "播放列表中有频道条目在流地址前就结束了。");
        put("Playlist ended before the final channel stream URL.", "播放列表在最后一个频道的流地址前就结束了。");
        put("ClearKey needs inline keys or a license URL.", "ClearKey 需要内嵌密钥或授权地址。");
        put("ClearKey playback requires a DASH stream.", "ClearKey 播放需要 DASH 流。");
        put("Forecast did not include upcoming days.", "预报中没有未来几天的数据。");
        put("Weather response was too large.", "天气返回数据过大。");
        put("Widevine DRM", "Widevine 加密");
        put("One per line · Name: Value", "每行一条 · 名称：值");
        put("↑↓ Channels     ←→ Schedule     OK Watch / reminders     Back Live TV",
                "↑↓ 频道     ←→ 节目表     确定键 观看 / 提醒     返回键 直播电视");
    }

    /** 天气状况单独一套词表：同一个 Clear 在按钮上是"清除"，在天气里必须是"晴"。 */
    private static final Map<String, String> WEATHER = new HashMap<>(16);

    static {
        WEATHER.put("Clear", "晴");
        WEATHER.put("Partly cloudy", "多云");
        WEATHER.put("Overcast", "阴");
        WEATHER.put("Fog", "雾");
        WEATHER.put("Drizzle", "毛毛雨");
        WEATHER.put("Rain", "雨");
        WEATHER.put("Snow", "雪");
        WEATHER.put("Thunderstorm", "雷雨");
        WEATHER.put("Mixed weather", "阴晴不定");
    }

    static String weather(String condition) {
        if (!chinese) return condition;
        String hit = WEATHER.get(condition);
        return hit == null ? condition : hit;
    }

    /** 读取已保存的语言选择，由 {@link SvaApplication#onCreate()} 调用一次。 */
    static void init(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        chinese = prefs.getBoolean(KEY_CHINESE, true);
    }

    static boolean isChinese() { return chinese; }

    static void setChinese(Context context, boolean value) {
        chinese = value;
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putBoolean(KEY_CHINESE, value).apply();
    }

    private static void put(String en, String zh) {
        MAP.put(en, zh);
    }

    /** 查表翻译；未命中返回原值。null 透传。 */
    static CharSequence t(CharSequence text) {
        return translate(text, 0);
    }

    /**
     * 最多套 3 层规则，够处理 "Photo source · Separate album · no link configured yet"
     * 这种前缀套后缀的拼接；层数上限同时防了规则互相咬死。
     */
    private static CharSequence translate(CharSequence text, int depth) {
        if (text == null) return null;
        if (!chinese) return text;
        String s = text.toString();
        if (s.isEmpty()) return text;
        String hit = MAP.get(s);
        if (hit != null) return hit;
        String trimmed = s.trim();
        if (!trimmed.equals(s)) {
            hit = MAP.get(trimmed);
            if (hit != null) return hit;
        }
        if (depth >= 3) return text;
        for (String[] rule : PREFIX) {
            if (s.startsWith(rule[0]))
                return rule[1] + translate(s.substring(rule[0].length()), depth + 1);
        }
        for (String[] rule : SUFFIX) {
            if (s.endsWith(rule[0]))
                return translate(s.substring(0, s.length() - rule[0].length()), depth + 1) + rule[1];
        }
        return text;
    }

    /** 整表翻译，用于 AlertDialog 的选项数组等非 TextView 场景。 */
    static String[] all(String[] values) {
        if (values == null) return null;
        String[] out = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            CharSequence t = t(values[i]);
            out[i] = t == null ? null : t.toString();
        }
        return out;
    }

    /**
     * 日期格式串本地化。设备 locale 已是中文，但 SimpleDateFormat 会沿用英文语序
     * （"周一, 19 10月 2026"），所以这里把语序掉过来。时间部分一律不动。
     */
    static String pattern(String enPattern) {
        if (!chinese) return enPattern;
        switch (enPattern) {
            case "EEE, dd MMM yyyy": return "M月d日 EEE";
            case "EEE, d MMM": return "M月d日 EEE";
            case "EEEE, d MMMM yyyy": return "M月d日 EEEE";
            case "EEEE, d MMM": return "M月d日 EEEE";
            case "EEE · h:mm a": return "M月d日 · h:mm a";
            default: return enPattern;
        }
    }

    /** "Add selected (3)" —— 括号里的数字是动态的，规则表接不住。 */
    static String selectedCount(int count) {
        if (!chinese) return "Add selected (" + count + ")";
        return "添加所选（" + count + "）";
    }

    /** 文件选择器多选时的说明行。 */
    static String selectedHint(int count) {
        if (!chinese) return count + " selected · press OK on files, then Add selected";
        return "已选 " + count + " 个 · 按确定键勾选文件，再点「添加所选」";
    }

    /** 覆盖同名文件的确认标题。 */
    static String replacePrompt(String name) {
        if (!chinese) return "Replace " + name + "?";
        return "替换「" + name + "」？";
    }

    /** 日期时间格式化用的 locale：切到英文时强制英文，否则沿用系统（中文）。 */
    static java.util.Locale locale() {
        return chinese ? java.util.Locale.getDefault() : java.util.Locale.ENGLISH;
    }

    /** "频道名 starts at 时间." —— 中间夹动态内容，无法用前后缀规则。 */
    static String startsAt(String channel, String time) {
        if (!chinese) return channel + " starts at " + time + ".";
        return channel + " 将于 " + time + " 开始。";
    }

    /** 频道列表标题：带复数变化，拆成两套整句。 */
    static String channelsCount(int visible, int total, int shown, int favourites) {
        if (!chinese) {
            return visible + " of " + total + " channels · " + shown + " shown · " + favourites
                    + " favourites\nPress OK to toggle visibility · Long-press to toggle favourite";
        }
        return visible + " / " + total + " 个频道 · 显示 " + shown + " · 收藏 " + favourites
                + "\n按确定键切换显示 · 长按切换收藏";
    }

    /**
     * 缓存/节目单摘要里的时间戳后缀。
     *
     * 单独抽出来有两个原因：① 要带上"更新于/上次刷新"这层措辞；
     * ② 日期格式必须跟语言走 —— 设备 locale 是 zh-CN，切到英文模式时
     * 若沿用默认 locale，会得到 "Updated 2026年10月19日 上午8:25" 这种半截货。
     */
    static String stamp(long updatedAt, boolean lastRefreshed) {
        if (updatedAt == 0L) return chinese ? "尚未刷新" : "Not refreshed yet";
        String when = java.text.DateFormat.getDateTimeInstance(
                java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT, locale())
                .format(new java.util.Date(updatedAt));
        if (!chinese) return (lastRefreshed ? "Last refreshed " : "Updated ") + when;
        return (lastRefreshed ? "上次刷新 " : "更新于 ") + when;
    }

    /** 本机频道缓存摘要：计数词（channel/channels）夹在前后缀之间，规则表接不住。 */
    static String channelCache(int channels, long updatedAt) {
        String stamp = stamp(updatedAt, false);
        if (!chinese) {
            return "On-device channel cache · " + channels
                    + (channels == 1 ? " channel · " : " channels · ") + stamp;
        }
        return "本机频道缓存 · " + channels + " 个频道 · " + stamp;
    }

    /** 节目单刷新摘要。 */
    static String guideSummary(long updatedAt) {
        String stamp = stamp(updatedAt, true);
        if (!chinese) return "Programme guide · daily refresh · " + stamp;
        return "节目单 · 每日刷新 · " + stamp;
    }

    /** 播放浮层的时段行：时间区间 + 剩余分钟，数字夹在中间。 */
    static String programmeWindow(String start, String end, long minutesLeft) {
        if (!chinese) return start + " – " + end + " · " + minutesLeft + " min left";
        return start + " – " + end + " · 还剩 " + minutesLeft + " 分钟";
    }

    /**
     * 收藏开关的 Toast。
     *
     * 原文是 (added ? "Added to" : "Removed from") + " favourites: " + name —— 三元表达式
     * 把句子劈成了两个字面量，前缀规则一个都匹配不上，之前四条调用点全是英文。
     */
    static String favouriteToast(boolean added, String name) {
        if (!chinese) return (added ? "Added to" : "Removed from") + " favourites: " + name;
        return (added ? "已加入收藏：" : "已移出收藏：") + name;
    }

    /** "Calendar refreshed · 3 events cached." —— 计数词夹在中间。 */
    static String calendarRefreshed(int events) {
        if (!chinese) return "Calendar refreshed · " + events
                + (events == 1 ? " event cached." : " events cached.");
        return "日历已刷新 · " + events + " 条日程已缓存。";
    }

    /** 游戏导入结果 Toast：added / duplicate(s) / skipped 三个数字都夹在中间。 */
    static String importSummary(int added, int duplicates, int skipped) {
        if (!chinese) {
            return added + " added · " + duplicates + " duplicate" + (duplicates == 1 ? "" : "s")
                    + " · " + skipped + " skipped";
        }
        return "新增 " + added + " 个 · 重复 " + duplicates + " 个 · 跳过 " + skipped + " 个";
    }

    /** YouTube 卡片列表里的行说明：类型 + 是否缺封面 + 地址，三段都夹着动态内容。 */
    static String youtubeRowDetail(boolean video, boolean hasArt, String url) {
        if (!chinese) {
            return (video ? "VIDEO" : "PLAYLIST") + (hasArt ? "" : "  ·  Add art") + "  ·  " + url;
        }
        return (video ? "视频" : "播放列表") + (hasArt ? "" : "  ·  未设封面") + "  ·  " + url;
    }

    /** 手柄重映射提示："Button 3 of 4" + 按键名。 */
    static String buttonOf(int step, int total, String keyName) {
        if (!chinese) return "Button " + step + " of " + total + "\n\n" + keyName;
        return "请按第 " + step + " 个键（共 " + total + " 个）\n\n" + keyName;
    }

    /** 公共日历缓存摘要。 */
    static String calendarCache(int events, String updated) {
        if (!chinese) return "Public calendar cache · " + events + " events · " + updated;
        return "公共日历缓存 · " + events + " 个日程 · " + updated;
    }
}
