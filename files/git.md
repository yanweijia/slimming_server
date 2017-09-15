# git 相关配置
 克隆代码到本地后执行以下操作,使本地master分支追踪个人远程仓库的dev分支
### 查看本地分支追踪远程分支情况
`git branch -vv`
## 删除本地分支追踪远程分支关系
`git branch --unset-upstream`
## 指定本地master分支追踪远程origin仓库dev分支
`git branch -u origin/dev`
## 将本地master分支重命名为dev
`git branch -m master dev`