USE [master]
GO
/****** Object:  Database [QLQuanBi-A]    Script Date: 16/12/2021 9:48:34 SA ******/
CREATE DATABASE [QLQuanBi-A]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLQuanBi-A', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLQuanBi-A.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLQuanBi-A_log', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLQuanBi-A_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QLQuanBi-A] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLQuanBi-A].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLQuanBi-A] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLQuanBi-A] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLQuanBi-A] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLQuanBi-A] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLQuanBi-A] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET RECOVERY FULL 
GO
ALTER DATABASE [QLQuanBi-A] SET  MULTI_USER 
GO
ALTER DATABASE [QLQuanBi-A] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLQuanBi-A] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLQuanBi-A] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLQuanBi-A] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLQuanBi-A] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QLQuanBi-A] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLQuanBi-A', N'ON'
GO
ALTER DATABASE [QLQuanBi-A] SET QUERY_STORE = OFF
GO
USE [QLQuanBi-A]
GO
/****** Object:  Table [dbo].[Ban]    Script Date: 16/12/2021 9:48:34 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ban](
	[MaBan] [int] NOT NULL,
	[TenBan] [nvarchar](50) NULL,
	[LoaiBan] [nvarchar](50) NULL,
	[TrangThai] [nvarchar](50) NULL,
	[DonGia] [float] NULL,
 CONSTRAINT [PK_Ban] PRIMARY KEY CLUSTERED 
(
	[MaBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[MaDV] [char](5) NOT NULL,
	[Ten] [nvarchar](50) NULL,
	[Gia] [float] NULL,
	[SoLuong] [int] NULL,
	[Hinh] [nvarchar](50) NULL,
 CONSTRAINT [PK_DichVuu] PRIMARY KEY CLUSTERED 
(
	[MaDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonHang]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonHang](
	[MaDH] [char](5) NOT NULL,
	[MaKH] [char](5) NULL,
	[MaDHDV] [char](5) NULL,
	[MaDHB] [char](5) NULL,
	[MaNV] [char](5) NULL,
	[NgayTao] [date] NULL,
	[KhuyenMai] [int] NULL,
	[TongTien] [float] NULL,
 CONSTRAINT [PK_DonHang] PRIMARY KEY CLUSTERED 
(
	[MaDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonHangBan]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonHangBan](
	[MaDHB] [char](5) NOT NULL,
	[MaBan] [int] NULL,
	[TGBatDau] [time](1) NULL,
	[TGKetThuc] [time](1) NULL,
	[GioThue] [float] NULL,
	[Gia] [float] NULL,
	[TongTien] [float] NULL,
	[TrangThai] [nvarchar](100) NULL,
 CONSTRAINT [PK_HoaDonBan] PRIMARY KEY CLUSTERED 
(
	[MaDHB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonHangDV]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonHangDV](
	[MaDHDV] [char](5) NOT NULL,
	[MaDV] [char](5) NULL,
	[Gia] [float] NULL,
	[SoLuong] [int] NULL,
	[TongTien] [float] NULL,
	[TrangThai] [nvarchar](100) NULL,
 CONSTRAINT [PK_DonHangDV] PRIMARY KEY CLUSTERED 
(
	[MaDHDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [char](5) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](5) NULL,
	[SDT] [char](10) NULL,
	[MaNV] [char](5) NOT NULL,
	[NgayDK] [date] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [char](5) NOT NULL,
	[MatKhau] [varchar](10) NULL,
	[HoTen] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](5) NULL,
	[SDT] [char](10) NULL,
	[VaiTro] [bit] NULL,
	[Email] [varchar](50) NULL,
	[Hinh] [varchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (1, N'Bàn Bi-a Dragon', N'Bi-a Lỗ', N'Trống', 40000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (2, N'Bàn Bi-a 9020', N'Bi-a Lỗ', N'Trống', 50000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (3, N'Bàn Bi-a Alileex 9020', N'Bi-a Lỗ', N'Trống', 55000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (4, N'Bàn Bi-a Seri 4', N'Bi-a Phăng', N'Trống', 60000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (5, N'Bàn Bi-a 9017 Gralit', N'Bi-a Phăng', N'Trống', 65000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (6, N'Bàn Bi-a Seri 6', N'Bi-a pool', N'Trống', 70000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (7, N'Bàn Bi-a Liên Doanh', N'Bi-a carom', N'Trống', 75000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (8, N'Bàn Bi-a 9081', N'Bi-a snooker', N'Trống', 80000)
INSERT [dbo].[Ban] ([MaBan], [TenBan], [LoaiBan], [TrangThai], [DonGia]) VALUES (9, N'Bàn Bi-a 6 lỗ', N'Bi-a pool', N'Trống', 85000)
GO
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV001', N'Coca cola', 10000, 100, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV002', N'Bia', 10000, 50, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV003', N'Cà phê ', 12000, 40, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV004', N'Bạc xỉu', 15000, 60, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV005', N'Sữa tươi ', 15000, 80, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV006', N'Sting', 10000, 60, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV007', N'Redbull', 15000, 80, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV008', N'Pepsi', 12000, 20, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV009', N'Mirinda', 12000, 50, NULL)
INSERT [dbo].[DichVu] ([MaDV], [Ten], [Gia], [SoLuong], [Hinh]) VALUES (N'DV010', N'Number one', 10000, 80, NULL)
GO
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH001', N'KH001', N'HDV01', N'DHB01', N'NV002', CAST(N'2021-09-12' AS Date), 10, 85500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH002', N'KH001', N'HDV02', N'DHB02', N'NV001', CAST(N'2021-12-14' AS Date), 10, 85500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH003', N'KH004', N'HDV03', N'DHB03', N'NV003', CAST(N'2021-08-12' AS Date), 10, 85500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH004', N'KH004', N'HDV04', N'DHB04', N'NV002', CAST(N'2021-09-14' AS Date), 10, 171000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH005', N'KH004', N'HDV05', N'DHB05', N'NV003', CAST(N'2021-05-14' AS Date), 10, 108000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH006', N'KH003', N'HDV06', N'DHB06', N'NV003', CAST(N'2021-08-14' AS Date), 10, 81000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH007', N'KH004', N'HDV07', N'DHB07', N'NV003', CAST(N'2021-12-15' AS Date), 10, 144000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH008', N'KH010', N'HDV08', N'DHB08', N'NV006', CAST(N'2021-12-15' AS Date), 10, 144000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH009', N'KH008', N'HDV09', N'DHB09', N'NV008', CAST(N'2021-12-15' AS Date), 10, 202500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH010', N'KH009', N'HDV10', N'DHB10', N'NV006', CAST(N'2021-12-15' AS Date), 10, 193500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH011', N'KH007', N'HDV11', N'DHB11', N'NV005', CAST(N'2021-12-15' AS Date), 10, 79200)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH012', N'KH005', N'HDV12', N'DHB12', N'NV009', CAST(N'2021-12-15' AS Date), 10, 93600)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH013', N'KH010', N'HDV13', N'DHB13', N'NV002', CAST(N'2021-12-15' AS Date), 10, 117000)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH014', N'KH009', N'HDV14', N'DHB14', N'NV005', CAST(N'2021-12-15' AS Date), 10, 94500)
INSERT [dbo].[DonHang] ([MaDH], [MaKH], [MaDHDV], [MaDHB], [MaNV], [NgayTao], [KhuyenMai], [TongTien]) VALUES (N'DH015', N'KH004', N'HDV15', N'DHB15', N'NV003', CAST(N'2021-12-15' AS Date), 10, 103500)
GO
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB01', 1, CAST(N'08:30:00' AS Time), CAST(N'10:30:00' AS Time), 2, 50000, 100000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB02', 4, CAST(N'14:45:00' AS Time), CAST(N'16:45:00' AS Time), 2, 70000, 140000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB03', 2, CAST(N'10:30:00' AS Time), CAST(N'12:00:00' AS Time), 1.5, 50000, 75000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB04', 5, CAST(N'07:00:00' AS Time), CAST(N'08:00:00' AS Time), 1, 70000, 70000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB05', 6, CAST(N'22:55:00' AS Time), CAST(N'23:55:00' AS Time), 1, 70000, 70000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB06', 1, CAST(N'23:01:00' AS Time), CAST(N'23:30:00' AS Time), 0.47, 40000, 19333.32, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB07', 5, CAST(N'10:35:00' AS Time), CAST(N'12:35:00' AS Time), 2, 65000, 130000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB08', 9, CAST(N'10:42:00' AS Time), CAST(N'11:42:00' AS Time), 1, 85000, 85000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB09', 7, CAST(N'10:43:00' AS Time), CAST(N'12:43:00' AS Time), 2, 75000, 150000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB10', 5, CAST(N'10:45:00' AS Time), CAST(N'13:45:00' AS Time), 3, 65000, 195000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB11', 1, CAST(N'20:11:00' AS Time), CAST(N'21:11:00' AS Time), 1, 40000, 40000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB12', 1, CAST(N'20:22:00' AS Time), CAST(N'22:22:00' AS Time), 2, 40000, 80000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB13', 2, CAST(N'20:50:00' AS Time), CAST(N'22:50:00' AS Time), 2, 50000, 100000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB14', 7, CAST(N'21:02:00' AS Time), CAST(N'22:02:00' AS Time), 1, 75000, 75000, N'Đã thanh toán')
INSERT [dbo].[DonHangBan] ([MaDHB], [MaBan], [TGBatDau], [TGKetThuc], [GioThue], [Gia], [TongTien], [TrangThai]) VALUES (N'DHB15', 9, CAST(N'21:03:00' AS Time), CAST(N'22:03:00' AS Time), 1, 85000, 85000, N'Đã thanh toán')
GO
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV01', N'DV001', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV02', N'DV002', 10000, 5, 50000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV03', N'DV002', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV04', N'DV005', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV05', N'DV006', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV06', N'DV006', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV07', N'DV007', 15000, 2, 30000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV08', N'DV005', 15000, 5, 75000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV09', N'DV007', 15000, 5, 75000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV10', N'DV006', 10000, 2, 20000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV11', N'DV008', 12000, 4, 48000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV12', N'DV003', 12000, 2, 24000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV13', N'DV004', 15000, 2, 30000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV14', N'DV010', 10000, 3, 30000, N'Đã thanh toán')
INSERT [dbo].[DonHangDV] ([MaDHDV], [MaDV], [Gia], [SoLuong], [TongTien], [TrangThai]) VALUES (N'HDV15', N'DV004', 15000, 2, 30000, N'Đã thanh toán')
GO
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH001', N'Cong Thanh', N'Nam', N'0384592702', N'NV001', CAST(N'2021-05-05' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH002', N'An Thuyen ', N'Nam', N'0942426205', N'NV002', CAST(N'2021-01-10' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH003', N'Cong Thanh', N'Nam', N'0384592702', N'NV001', CAST(N'2021-05-05' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH004', N'Nguyen Van A', N'Nam', N'0384592702', N'NV003', CAST(N'2021-10-10' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH005', N'Le Thi B', N'Nu', N'0942426205', N'NV004', CAST(N'2021-05-10' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH006', N'Tran Anh Tu', N'Nam', N'0325520808', N'NV005', CAST(N'2021-10-05' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH007', N'Le Doan Tinh', N'Nam', N'0382240220', N'NV006', CAST(N'2021-07-01' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH008', N'Le Thi Quy', N'Nu', N'0387270501', N'NV007', CAST(N'2021-02-02' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH009', N'Le Thi Anh Thu', N'Nu', N'0942425566', N'NV008', CAST(N'2021-03-03' AS Date))
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [GioiTinh], [SDT], [MaNV], [NgayDK]) VALUES (N'KH010', N'Le Phi Hung', N'Nam', N'0922257267', N'NV009', CAST(N'2021-10-04' AS Date))
GO
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV001', N'1234', N'Cong Thanh', N'Nam', N'0384592702', 1, N'thanh@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV002', N'1234', N'An Thuyen', N'Nam', N'0942426202', 1, N'thuyen@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV003', N'1234', N'Nguyễn Văn B', N'Nữ', N'0384592702', 0, N'B@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV004', N'1234', N'Nguyen Thi G', N'Nữ', N'0942426205', 0, N'G@gamil.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV005', N'1234', N'Phan Kieu Anh', N'Nữ', N'0382240220', 1, N'anh@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV006', N'1234', N'Le Hoang Trung', N'Nam', N'0925567422', 1, N'trung@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV007', N'1234', N'Le Van Minh', N'Nam', N'0386602020', 1, N'minh@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV008', N'1234', N'Le Thanh Duong ', N'Nam', N'0320052201', 0, N'duong@gmail.com', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MatKhau], [HoTen], [GioiTinh], [SDT], [VaiTro], [Email], [Hinh]) VALUES (N'NV009', N'1234', N'Vo Kieu Diem', N'Nu', N'0942977263', 0, N'diem@gmail.com', NULL)
GO
ALTER TABLE [dbo].[DonHang]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_DonHangBan] FOREIGN KEY([MaDHB])
REFERENCES [dbo].[DonHangBan] ([MaDHB])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DonHang] CHECK CONSTRAINT [FK_DonHang_DonHangBan]
GO
ALTER TABLE [dbo].[DonHang]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_DonHangDV] FOREIGN KEY([MaDHDV])
REFERENCES [dbo].[DonHangDV] ([MaDHDV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DonHang] CHECK CONSTRAINT [FK_DonHang_DonHangDV]
GO
ALTER TABLE [dbo].[DonHang]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_KhachHang] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DonHang] CHECK CONSTRAINT [FK_DonHang_KhachHang]
GO
ALTER TABLE [dbo].[DonHang]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[DonHang] CHECK CONSTRAINT [FK_DonHang_NhanVien]
GO
ALTER TABLE [dbo].[DonHangBan]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonBan_Ban] FOREIGN KEY([MaBan])
REFERENCES [dbo].[Ban] ([MaBan])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DonHangBan] CHECK CONSTRAINT [FK_HoaDonBan_Ban]
GO
ALTER TABLE [dbo].[DonHangDV]  WITH CHECK ADD  CONSTRAINT [FK_DonHangDV_DichVu] FOREIGN KEY([MaDV])
REFERENCES [dbo].[DichVu] ([MaDV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DonHangDV] CHECK CONSTRAINT [FK_DonHangDV_DichVu]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_NhanVien]
GO
/****** Object:  StoredProcedure [dbo].[sp_DoanhThuTheoThang]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_DoanhThuTheoThang] (@thang INT)
AS BEGIN
	SELECT C.MaBan, C.LoaiBan,COUNT(A.MaKH) AS 'Số lượng KH' , SUM(A.TONGTIEN) AS 'Doanh thu' 
	FROM DONHANG A INNER JOIN DONHANGBAN B 
	ON A.MaDHB = B.MaDHB INNER JOIN BAN C 
	ON B.MaBan = C.MaBan 
	WHERE MONTH(A.NGAYTAO) =  @thang
	GROUP BY MONTH(A.NGAYTAO), C.MaBan, C.LOAIBAN
END
GO
/****** Object:  StoredProcedure [dbo].[sp_DVBanChayNhat]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_DVBanChayNhat]
AS BEGIN
	SELECT A.MADV, B.TEN, A.GIA, SUM(A.SOLUONG) AS 'Số lượng', SUM(A.TONGTIEN) AS 'Doanh thu'
	FROM DONHANGDV A INNER JOIN DICHVU B 
	ON A.MaDV = B.MADV 
	GROUP BY A.MADV, B.TEN, A.GIA
	ORDER BY SUM(A.TONGTIEN) DESC
END
GO
/****** Object:  StoredProcedure [dbo].[sp_LuongNguoiChoiTheoThang]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_LuongNguoiChoiTheoThang]
AS BEGIN
	SELECT MONTH(NGAYTAO) AS 'Tháng', COUNT(MAKH) AS 'Số lượng KH' 
	FROM DONHANG 
	GROUP BY MONTH(NGAYTAO)
END
GO
/****** Object:  StoredProcedure [dbo].[sp_NhanVienXS]    Script Date: 16/12/2021 9:48:35 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_NhanVienXS]
AS BEGIN
	SELECT  A.MANV,B. HOTEN, B.SDT, COUNT(MADH) AS 'Số lượng ĐH' 
	FROM DONHANG A INNER JOIN NHANVIEN B ON A.MaNV = B.MaNV
	GROUP BY A.MANV, B.HoTen, B.SDT
	ORDER BY COUNT(MADH) DESC

END
GO
USE [master]
GO
ALTER DATABASE [QLQuanBi-A] SET  READ_WRITE 
GO
