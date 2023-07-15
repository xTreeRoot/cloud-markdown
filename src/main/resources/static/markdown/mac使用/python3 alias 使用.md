Python3.X替换mac自带的python2.7版本
由于mac自带Python，因此：你会发现终端：执行 python --version 返回的是2.7版本；执行python3 --version返回的是3.7版本。
需要配置环境变量，替换mac自带的python版本。

操作如下：

查看python3安装在了哪里： which python3
我的显示安装路径为：/usr/local/bin/python3

在.bash_profile中配置环境变量：open .bash_profile
加入：alias python="/usr/local/bin/python3"

保存.bash_profile
执行 source ~/.bash_profile
执行python——你会发现返回的是3.7版本

关终端，重新打开终端：输入python，你会发现依然返回2.7版本。
（此现象为：只有每次执行source ~/.bash_profile 后，所配置的环境变量才生效。
每次重启终端后，配置的不生效。）
原因是：zsh加载的是 ~/.zshrc文件，而 ‘.zshrc’ 文件中并没有定义任务环境变量。
如遇到此问题：输入open .zshrc
在~/.zshrc文件最后，增加一行： source ~/.bash_profile


