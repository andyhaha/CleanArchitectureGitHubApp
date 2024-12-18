# 项目名称

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

**🔥注意事项：** 在local.properties文件中加一个变量，API_TOKEN=xxx， 把“xxx”替换成自己的GitHub API token

## 项目简介

这是一个 GitHub 用户搜索应用程序，用户可以在首页搜索 GitHub 用户并显示用户列表。用户可以点击列表中的某个用户，进入该用户的详细信息页面，展示用户的基本信息以及他们创建的仓库。

## 架构简介

该项目遵循 **Clean Architecture** 原则，并采用 **MVVM**（Model-View-ViewModel）架构模式，以实现更好的模块化和关注点分离。通过将不同的业务逻辑与 UI 逻辑解耦，项目更容易进行测试和维护。

以下是项目结构的概述，遵循您的示例风格：

## 模块概述

该项目分为多个模块：

- **:app** - 主 Android 应用模块，协调特性模块和库模块。
- **:feature:home** - 显示 GitHub 用户列表，支持搜索并使用 Room 存储搜索结果。
- **:feature:details** - 显示选定用户的详细信息，包括其个人资料和仓库。
- **:libs:network** - 使用 Retrofit 和 Moshi 进行网络请求和数据序列化。
- **:libs:common** - 仅 Kotlin 模块，提供应用中常用的工具函数和公共类。

## 使用的技术

该项目使用了以下技术栈：

- **Clean Architecture**: 实现了“清洁”架构，提供更好的关注点分离，提高了代码的可维护性和可测试性。
- **Multi-Modules**：将代码库划分为更小、可重用且可测试的模块，提高可扩展性，减少依赖，并简化大型项目的管理和维护。
- **MVVM**: 实现了 MVVM 架构模式，将视图逻辑与业务逻辑分离。
- **Flow & ViewModel**: 用于以生命周期感知的方式管理 UI 相关数据。
- **Jetpack Compose**: 用于构建用户界面，简化了声明式编程方式的 UI 开发。
- **Dagger Hilt**: Dagger Hilt 简化了跨模块的依赖注入，通过便捷的单元和 UI 测试的 mock 支持，增强了可测试性。
- **Retrofit**: 用于网络请求，简化了 API 调用的过程。
- **Room Database**: 提供本地数据库支持，方便数据存储和查询。
- **Moshi**: 用于解析 JSON 数据，提高了数据序列化和反序列化的效率。
- **Coil**: 用于图像加载，提供平滑的图像处理和缓存功能。
- **GitHub Authorize**: 使用 GitHub OAuth 进行授权，确保用户可以安全地访问和搜索 GitHub 数据。
- **Room Database Testing**: 通过单元测试和仪器测试，确保本地 SQLite 数据库中的数据存储的可靠性，测试 CRUD 操作和数据完整性。
- **UI Testing**：通过 Compose UI 测试验证 HomeContent、SearchHistory 和 HomeSearchBar 等基于 Compose 的组件的功能和用户体验，确保交互流畅且渲染正确。

## 贡献

欢迎对这个项目提出建议和贡献！请创建一个 Pull Request 或提交问题。

## 许可证

版权所有 (c) [2024] [Andy]

特此免费授予使用、复制、修改、合并、发布、分发、再许可和/或出售本软件的副本，以及允许他人这样做的权限，但须遵守以下条件：

上述版权声明和本许可声明应包含在本软件的所有副本或重要部分中。

本软件按“原样”提供，不附任何明示或暗示的担保，包括但不限于适销性、特定用途的适用性和非侵权的担保。在任何情况下，作者或版权持有者均不对因使用本软件或与本软件有关的行为所引起的任何索赔、损害或其他责任负责。
