操作步骤：
打开终端输入：

vim ~/.bash_profile

按一下 i 表示插入，如将 /Users/xxx/Library/Python/3.8/bin 加入 PATH

export "/Users/xxx/Library/Python/3.8/bin:$PATH"

Mac 下复制文件路径的方式：选中文件复制，然后再终端中粘贴，即可

按下键盘左上角的 Esc 键退出编辑，然后按 :输入 wq 保存即可退出

再通过 source ~/.zshrc   重新加载一下，完成

