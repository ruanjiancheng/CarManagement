<template>
  <div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          fixed
          prop="staffId"
          label="维修员工号"
          width="150">
      </el-table-column>
      <el-table-column
          fixed
          prop="name"
          label="维修员名称"
          width="150">
      </el-table-column>
      <el-table-column
          prop="account"
          label="账号"
          width="150">
      </el-table-column>
      <el-table-column
          prop="password"
          label="密码"
          width="150">
      </el-table-column>
      <el-table-column
          prop="unitPrice"
          label="单价"
          width="150">
      </el-table-column>
      <el-table-column
          prop="workerType"
          label="工种"
          width="150">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
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
          <el-button @click="editor(scope.row)" type="text" size="small">编辑</el-button>
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
  </div>
</template>

<script>
export default {
  name: "workerList",
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
    }
  },
  props: {
    ruleForm: {
      highPrice: "",
      lowPrice: "",
      name: ""
    },
    isActive: Boolean
  },
  watch: {
    ruleForm: {
      handler(newRuleForm, oldRuleForm) {
        const that = this
        that.tmpList = null
        that.total = null
        that.tableData = null
        const url = this.$url + '/api/staff/queryWorker'
        console.log(url, newRuleForm)
        axios.post(url, newRuleForm).then(function (resp) {
          console.log(resp.data)
          that.tmpList = resp.data.data
          for (let i = 0; i < that.tmpList.length; i++) {
            that.tmpList[i].workerType = that.$workerType[that.tmpList[i].workerType]
          }
          console.log(that.tmpList)
          that.total = resp.data.data.length
          let start = 0, end = that.pageSize
          let length = that.tmpList.length
          let ans = (end < length) ? end : length
          that.tableData = that.tmpList.slice(start, ans)
        })
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    select(row) {
      console.log(row)
    },
    deleteTeacher(row) {
      const that = this
      const url = this.$url + '/api/staff/delete'
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
      this.$router.push({
        path: '/editorWorker',
        query: {
          id: row.id
        }
      })
    }
  }
}
</script>

<style scoped>

</style>