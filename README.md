# QL-PHAM-NHAN-TRAI-GIAM-V1

Prison Management System
Prison Management System là một ứng dụng được thiết kế để hỗ trợ quản lý thông tin trại giam, phạm nhân, tài khoản người dùng, và các đăng ký thăm gặp. Ứng dụng giúp tối ưu hóa quy trình quản lý trại giam, đảm bảo tính chính xác và hiệu quả trong việc theo dõi và quản lý dữ liệu của từng trại.

🛠️ Tính năng chính
Quản lý tài khoản: Thêm, xóa và xác thực tài khoản người dùng với phân quyền cụ thể.
Quản lý trại giam: Theo dõi danh sách trại giam, cập nhật thông tin và quản lý tình trạng quá tải.
Quản lý phạm nhân: Thêm mới, cập nhật thông tin, và xóa phạm nhân khỏi hệ thống.
Quản lý đăng ký thăm gặp: Ghi nhận và quản lý các đăng ký thăm gặp của thân nhân phạm nhân.
Báo cáo và thống kê: Hỗ trợ các báo cáo về số lượng phạm nhân, tình trạng của trại giam, và các đăng ký thăm gặp.
🚀 Cài đặt và sử dụng
Yêu cầu hệ thống
Java JDK 11+
NetBeans IDE (hoặc IDE khác hỗ trợ Java)
Thư viện Gson: Được sử dụng để xử lý dữ liệu JSON.
Hướng dẫn cài đặt
Clone dự án:
bash
Copy code
git clone https://github.com/username/prison-management-system.git
Cài đặt thư viện Gson:
Tải và thêm thư viện Gson vào project trong NetBeans.
Chạy ứng dụng:
Mở dự án trong NetBeans và chạy QLPHAMNHANTRAIGIAMV1.java.
📷 Hình ảnh giao diện ứng dụng
Giao diện Đăng Nhập

Giao diện Quản lý Trại Giam

Giao diện Quản lý Phạm Nhân

Giao diện Đăng Ký Thăm Gặp

Lưu ý: Để hiển thị hình ảnh, bạn cần tải ảnh lên thư mục của dự án và thay path/to/your/image.png bằng đường dẫn đến ảnh tương ứng.

🗂️ Cấu trúc dự án
css
Copy code
src
├── access
├── components
├── data
├── displays
├── models
│ ├── Account.java
│ ├── Prison.java
│ ├── Prisoner.java
│ ├── PrisonSummary.java
│ └── Registration.java
├── services
│ ├── AccountService.java
│ ├── PrisonService.java
│ ├── PrisonerService.java
│ ├── RegistrationService.java
│ └── Validator.java
└── utils
└── RefreshablePanel.java
📚 Chi tiết chức năng
Quản lý tài khoản
Hỗ trợ đăng nhập, đăng xuất, phân quyền, và quản lý danh sách tài khoản người dùng.
Quản lý trại giam
Thêm mới trại giam, cập nhật và xóa trại giam, xem tình trạng trại giam như tình trạng quá tải và số lượng phạm nhân.
Quản lý phạm nhân
Thêm mới phạm nhân vào hệ thống, cập nhật thông tin, tìm kiếm và xóa phạm nhân khi cần.
Quản lý đăng ký thăm gặp
Hỗ trợ quản lý đăng ký thăm gặp của người thân, bao gồm kiểm tra trạng thái đăng ký và tìm kiếm các đăng ký theo ngày hoặc tên người đăng ký.
🛡️ Bảo mật
Ứng dụng có cơ chế bảo mật cơ bản:

Kiểm tra thông tin đăng nhập để ngăn chặn truy cập trái phép.
Đóng gói dữ liệu và chỉ cung cấp các phương thức truy cập qua API.
📈 Hướng phát triển
Tăng cường bảo mật: Thêm xác thực hai lớp (2FA) và mã hóa mật khẩu nâng cao.
Chức năng báo cáo chi tiết hơn: Cung cấp báo cáo chi tiết về phạm nhân, tình trạng trại giam, và đăng ký thăm gặp.
Phát triển giao diện người dùng: Tạo giao diện trực quan và dễ sử dụng hơn.
Tích hợp API: Kết nối với các hệ thống quản lý khác để tăng tính linh hoạt trong quản lý dữ liệu.
📝 Đóng góp
Nếu bạn muốn đóng góp cho dự án này, hãy tạo một Pull Request hoặc liên hệ với chúng tôi qua email@example.com.

📄 Giấy phép
Dự án này được cấp phép theo MIT License.
