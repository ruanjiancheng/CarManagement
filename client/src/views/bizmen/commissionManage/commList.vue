<template>
  <div>
    <el-card>
      <!--      <el-button round @click="add()">新建</el-button>-->
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            fixed
            prop="staffId"
            label="员工 ID"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="commissionId"
            label="委托书编号"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="numberPlace"
            label="车牌号"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="clientId"
            label="客户 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="type"
            label="维修类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="classification"
            label="作业类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="settlementMode"
            label="结算类型"
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="description"
            label="故障描述"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="dueDate"
            label="预计完成时间"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="date"
            label="登记时间"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="statusZh"
            label="当前状态"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作"
            width="150">
          <template slot-scope="scope">
            <el-popconfirm
                confirm-button-text='删除'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="删除不可复原"
                @confirm="deleteTeacher(scope.row)"
            >
              <el-button slot="reference" type="text" size="small">删除</el-button>
            </el-popconfirm>
            <el-button @click="editor(scope.row)" type="text" size="small" v-if="scope.row.status === 0">创建工单</el-button>
            <el-button @click="deal(scope.row)" type="text" size="small" v-if="scope.row.status === 2">付款</el-button>
            <el-button @click="find(scope.row)" type="text" size="small" v-if="scope.row.status === 1">查看维修进度</el-button>
            <el-button @click="list(scope.row)" type="text" size="small">详细信息</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="changePage"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "repairmentList",
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = null
    that.total = null
    that.tableData = null
    const url = this.$url + '/api/comm/getAllCommissions'
    axios.get(url).then(function (resp) {
      console.log(resp.data.data)
      that.tmpList = resp.data.data
      for (let i = 0; i < that.tmpList.length; i++) {
        that.tmpList[i].statusZh = that.$dict[that.tmpList[i].status]
      }
      console.log(that.tmpList)
      that.total = resp.data.data.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    })
  },
  methods: {
    deleteTeacher(row) {
      const that = this
      const url = this.$url + '/api/comm/delete'
      console.log(row)
      axios.get(url + '?id=' + row.id).then(function (resp) {
        console.log(resp)
        if (resp.data.code === 1000) {
          that.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          window.location.reload()
        } else {
          that.$message({
            showClose: true,
            message: '删除出错，请查询数据库连接',
            type: 'error'
          });
        }
      }).catch(function (error) {
        that.$message({
          showClose: true,
          message: '删除出错，存在外键依赖',
          type: 'error'
        });
      })
    },
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
    editor(row) {
      const that = this
      const url = this.$url + '/api/order/insert'
      let form = {commissionId: row.id, status: 0}
      axios.post(url, form).then(function (resp) {
        console.log(resp)
        if (resp.data.code === 1000) {
          axios.post(that.$url + '/api/comm/setCommStatus', {commissionID: row.id, status: 1}).then(function (resp) {
            that.$message({
              showClose: true,
              message: '添加成功',
              type: 'success'
            });
            window.location.reload()
          })
        } else {
          that.$message({
            showClose: true,
            message: '出错，请查询数据库连接',
            type: 'error'
          });
        }
      }).catch(function (error) {
        that.$message({
          showClose: true,
          message: '出错，存在外键依赖',
          type: 'error'
        });
      })
    },
    list(row) {
      // TODO 展开
      const that = this
      axios.get(this.$url + '/api/comm/getOrderByCommID?id=' + row.id).then(function (resp) {
        that.$router.push({
          path: '/detail',
          query: {
            id: resp.data.data.id,
          }
        })
      }).catch(function (){
        that.$message({
          showClose: true,
          message: '请先创建工单',
          type: 'error'
        });
      })

    },
    deal(row) {
      const that = this
      axios.get(this.$url + '/api/comm/getTotalCost?commissionID=' + row.id).then(function (resp1) {
        console.log('tttt11111: ', resp1)
        that.$confirm('是否付款 ' + resp1.data.data + ' ?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'success'
        }).then(() => {
          const url = that.$url + '/api/comm/setCommStatus'
          let form = {commissionId: row.id, status: 0}
          axios.post(url, {commissionID: row.id, status: 3}).then(function (resp) {
            console.log('tttttttttttttttt: ', resp)
            if (resp.data.code === 1000) {
              that.$message({
                showClose: true,
                message: '添加成功',
                type: 'success'
              });
              window.location.reload()
            } else {
              that.$message({
                showClose: true,
                message: '出错，请查询数据库连接',
                type: 'error'
              });
            }
          }).catch(function (error) {
            that.$message({
              showClose: true,
              message: '出错，存在外键依赖',
              type: 'error'
            });
          })
        }).catch(() => {
          that.$message({
            type: 'info',
            message: '付款失败'
          });
        });
      })



    },
    add() {
      this.$router.push({path: '/addRepairment'})
    },
    find(row) {
      // TODO 查看维修进度
    }
  }
}
</script>

<style scoped>

</style>