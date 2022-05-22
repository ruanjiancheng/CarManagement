<template>
  <div>
    <el-card>
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            fixed
            prop="repairmentID"
            label="维修项目 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="WorkerID"
            label="维修员 ID"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="workTime"
            label="工时"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="statusZh"
            label="状态"
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
              <el-button slot="reference" type="text" size="small" v-if="status === 0">删除</el-button>
            </el-popconfirm>
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
      id: null,
      name: null,
      status: null,
      tmpList: null,
      totalPrice: 0,
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = null
    that.total = null
    that.tableData = null
    that.id = this.$route.query.id
    const url2 = this.$url + '/api/order/query?id='
    axios.get(url2 + that.id).then(function (resp) {
      console.log('data: ', resp.data)
      that.status = resp.data.data.status
      that.tmpList = JSON.parse(resp.data.data.repairDetail)
      for (let i = 0; i < that.tmpList.length; i++) {
        that.tmpList[i].statusZh = that.$orderDict[that.tmpList[i].status]
      }
      console.log(that.tmpList)
      that.total = that.tmpList.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    })
  },
  methods: {
    select(row) {
      console.log(row)
    },
    deleteTeacher(row) {
      const that = this
      const url = this.$url + "/api/order/deleteDetail"
      this.ruleForm = {orderID: that.id, detailID: row.repairmentID}
      console.log(this.ruleForm)
      axios.post(url, this.ruleForm).then(function (resp) {
        console.log(resp)
        if (resp.data.code === 1000) {
          that.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          window.location.reload()
        }
        else {
          that.$message.error('删除失败，请检查数据库');
        }
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
      this.$router.push({
        path: '/repairmentAddMaterial',
        query: {
          id: row.id,
          name: row.name
        }
      })
    },
    list(row) {
      this.$router.push({
        path: '/repairmentDetail',
        query: {
          id: row.id
        }
      })
    },
    add() {
      this.$router.push({path: '/addRepairment'})
    }
  }
}
</script>

<style scoped>

</style>